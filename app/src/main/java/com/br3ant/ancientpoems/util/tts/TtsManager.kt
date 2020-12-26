package com.br3ant.ancientpoems.util.tts

import android.speech.tts.TextToSpeech
import com.blankj.utilcode.util.ToastUtils
import com.blankj.utilcode.util.Utils


/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020 12 12/26/20
 *     desc   :
 *     version: 1.0
 * </pre>
 */
object TtsManager {

    private var initialized: Boolean = false

    private lateinit var textToSpeech: TextToSpeech


    private fun setLanguage() {
        val supported: Int = textToSpeech.setLanguage(java.util.Locale.CHINA)
        if (supported != TextToSpeech.LANG_AVAILABLE && supported != TextToSpeech.LANG_COUNTRY_AVAILABLE) {
            ToastUtils.showShort("不支持的语言")
        } else {
            initialized = true
        }
    }

    fun init() {
        if (initialized) return

        textToSpeech = TextToSpeech(Utils.getApp()) { status ->
            if (status == TextToSpeech.SUCCESS) {
                setLanguage()
                textToSpeech.setSpeechRate(0.5f)
            }
        }
    }

    fun speech(tts: String) {
        if (this::textToSpeech.isInitialized && initialized) {
            textToSpeech.speak(tts, TextToSpeech.QUEUE_FLUSH, null);
        } else {
            ToastUtils.showShort("还未初始化")
        }
    }
}