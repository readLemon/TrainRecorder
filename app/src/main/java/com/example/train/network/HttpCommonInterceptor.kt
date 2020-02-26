package com.example.train.network

import okhttp3.Interceptor
import okhttp3.Response

/**
 * 拦截器,向请求头里添加公共参数
 * Created by chenyang
 * on 20-1-15
 */
class HttpCommonInterceptor : Interceptor {
    internal val mHearderHashMap = HashMap<String, String>()
    override fun intercept(chain: Interceptor.Chain): Response {
        val oldRequest = chain.request()

        //添加新的参数，添加到url中
        /*val authorizeUrlBuilder = oldRequest.url.newBuilder()
            .scheme(oldRequest.url.scheme)
            .host(oldRequest.url.host)

        */

        //新的请求
        val newRequest = with(oldRequest.newBuilder()) {
            method(oldRequest.method, oldRequest.body)
            if (mHearderHashMap.size > 0) {
                for (params in mHearderHashMap.entries) {
                    header(params.key, params.value)
                }
            }
            build()
        }

        return chain.proceed(newRequest)
    }

    fun addHeaderParams(key: String, value: String): HttpCommonInterceptor {
        mHearderHashMap.put(key, value)
        return this
    }

    fun addHeaderParams(key: String, value: Int) = addHeaderParams(key, value.toString())


    fun addHeaderParams(key: String, value: Float) = addHeaderParams(key, value.toString())


    fun addHeaderParams(key: String, value: Long) = addHeaderParams(key, value.toString())


    fun addHeaderParams(key: String, value: Double) = addHeaderParams(key, value.toString())

    fun addAllHeaderParams(map: HashMap<String, String>): HttpCommonInterceptor {
        mHearderHashMap.putAll(map)
        return this
    }

    fun build(): HttpCommonInterceptor {
        return this
    }
}
