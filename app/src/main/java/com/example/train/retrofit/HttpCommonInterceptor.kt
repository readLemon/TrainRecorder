package com.example.train.retrofit

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 拦截器,向请求头里添加公共参数
 * Created by chenyang
 * on 20-1-15
 */
class HttpCommonInterceptor: Interceptor {
    internal val mHearderHashMap = HashMap<String, String>()
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        //添加新的参数，添加到url中
        /*val authorizeUrlBuilder = oldRequest.url.newBuilder()
            .scheme(oldRequest.url.scheme)
            .host(oldRequest.url.host)

        */

        //新的请求
        val requesBuilder = oldRequest.newBuilder()
        requesBuilder.method(oldRequest.method, oldRequest.body)
        if (mHearderHashMap.size > 0) {
           for (params in mHearderHashMap.entries){
               requesBuilder.header(params.key, params.value)
           }
        }

        return chain.proceed(requesBuilder.build())
    }


}

class HttpCommonInterceptorGen() {

    val mHttpCommonInterceptor:HttpCommonInterceptor
    init {
       mHttpCommonInterceptor = HttpCommonInterceptor()
    }
    companion object {
        fun generate(body: HttpCommonInterceptorGen.() -> HttpCommonInterceptor): HttpCommonInterceptor{
            return with(HttpCommonInterceptorGen()) {
                body()
            }
        }
    }


    fun addHeaderParams(key: String, value: String): HttpCommonInterceptorGen {
        mHttpCommonInterceptor.mHearderHashMap.put(key,value)
        return this
    }

    fun addHeaderParams(key: String, value: Int): HttpCommonInterceptorGen {
        return addHeaderParams(key, value.toString())
    }

    fun addHeaderParams(key: String, value: Float): HttpCommonInterceptorGen {
        return addHeaderParams(key, value.toString())
    }

    fun addHeaderParams(key: String, value: Long): HttpCommonInterceptorGen {
        return addHeaderParams(key, value.toString())
    }

    fun addHeaderParams(key: String, value: Double): HttpCommonInterceptorGen {
        return addHeaderParams(key, value.toString())
    }

    fun addAllHeaderParams(map: HashMap<String, String>): HttpCommonInterceptorGen{
        mHttpCommonInterceptor.mHearderHashMap.putAll(map)
        return this
    }

    fun build(): HttpCommonInterceptor {
        return mHttpCommonInterceptor
    }

}

//object Builder{
//    val mHttpCommonInterceptor:HttpCommonInterceptor
//
//    init {
//       mHttpCommonInterceptor = HttpCommonInterceptor()
//    }
//
//}