package com.br3ant.ancientpoems.util.rxhttp;

import com.br3ant.ancientpoems.util.rxhttp.interceptor.TokenInterceptor;
import com.br3ant.utils.GsonUtil;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import rxhttp.RxHttp;
import rxhttp.wrapper.converter.GsonConverter;


/**
 * <pre>
 *     copyright: datedu
 *     @author : br3ant
 *     e-mail : xxx@xx
 *     time   : 2020/8/12
 *     desc   :
 *     version: 1.0
 * </pre>
 */
public class RxHttpUtils {
    public static void init() {

        RxHttp.init(new OkHttpClient.Builder()
                .addInterceptor(new TokenInterceptor())
                .readTimeout(20000, TimeUnit.MILLISECONDS)
                .writeTimeout(20000, TimeUnit.MILLISECONDS)
                .connectTimeout(20000, TimeUnit.MILLISECONDS).build());

//        RxHttp.setOnParamAssembly(p -> {
//            p.addHeader("token", UserHelper.getToken())
//                    .add("serviceVersion", "4.0")
//                    .add("schoolId", UserHelper.getSchoolId());
//            return p;
//        });

        RxHttp.setConverter(GsonConverter.create(GsonUtil.getInstance().getGson()));
    }
}
