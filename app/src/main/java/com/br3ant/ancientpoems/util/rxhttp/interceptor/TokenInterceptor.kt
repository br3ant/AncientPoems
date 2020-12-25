package com.br3ant.ancientpoems.util.rxhttp.interceptor

import androidx.annotation.Keep
import com.blankj.utilcode.util.LogUtils
import com.br3ant.ancientpoems.BuildConfig
import com.br3ant.ancientpoems.util.rxhttp.BaseResponse
import com.br3ant.utils.GsonUtil
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response
import okio.Buffer
import java.io.EOFException
import java.io.IOException
import java.nio.charset.StandardCharsets

/**
 * token 失效，自动刷新token，然后再次发送请求，用户无感知
 * User: ljx
 * Date: 2019-12-04
 * Time: 11:56
 */
class TokenInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): Response {
        val request: Request = chain.request()
        logForRequest(request)
        val originalResponse: Response = chain.proceed(request)
        val code = getCodeAndLogResponse(originalResponse)
        return originalResponse
    }


    private fun logForRequest(request: Request) {

        try {
            if (!logSwitch(request.url.toString())) return

            val requestBody = request.body

            if (requestBody != null) {
                val buffer = Buffer()
                requestBody.writeTo(buffer)
                val charset = requestBody.contentType()?.charset(UTF8)

                val body = buffer.readString(charset ?: UTF8)

                LogUtils.iTag(TAG, "method = ${request.method} -- url = ${request.url}?${body}&token=${request.header("token") ?: ""}")
            } else {
                LogUtils.iTag(TAG, "method = ${request.method} -- url = ${request.url}&token=${request.header("token") ?: ""}")
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

    private fun getCodeAndLogResponse(response: Response?): String? {

        val body = response?.body ?: return null
        try {
            val source = body.source()
            source.request(Long.MAX_VALUE)
            val buffer = source.buffer

            if (!isPlaintext(buffer)) {
                return null
            }
            if (body.contentLength() != 0L) {
                val string = buffer.clone().readString(body.contentType()?.charset(UTF8) ?: UTF8)
                if (logSwitch(response.request.url.toString())) {
                    LogUtils.iTag(TAG, string)
                }
                return GsonUtil.json2Bean(string, BaseResponse::class.java)?.code.toString()
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }

        return null
    }

    private fun isPlaintext(buffer: Buffer): Boolean {
        return try {
            val prefix = Buffer()
            val byteCount = if (buffer.size < 64) buffer.size else 64
            buffer.copyTo(prefix, 0, byteCount)
            for (i in 0..15) {
                if (prefix.exhausted()) {
                    break
                }
                val codePoint = prefix.readUtf8CodePoint()
                if (Character.isISOControl(codePoint) && !Character.isWhitespace(codePoint)) {
                    return false
                }
            }
            true
        } catch (e: EOFException) {
            false
        }
    }

    private fun logSwitch(url: String) = BuildConfig.DEBUG || url !in FILTER

    @Keep
    private class TokenBean {
        var token: String? = null
    }

    companion object {
        private val UTF8 = StandardCharsets.UTF_8
        private const val TAG = "OkHttp"

        //token刷新时间
        @Volatile
        private var SESSION_KEY_REFRESH_TIME: Long = 0

        private val FILTER = listOf("")
    }
}