package com.example.train.network

import com.example.train.config.ApiConfig
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

/**
 * Created by chenyang
 * on 20-1-15
 */
class RetrofitServiceManager {


    companion object {
        private val DEFAULT_TIME_OUT = 5L  //超时时间5s
        private val DEFAULT_READ_TIME_OUT = 10L
        /**
         * 获取实例
         */
        fun getInstance(): RetrofitServiceManager {
            return SingleHolder.INSTANCE
        }
    }

    private val mRetrofit: Retrofit

    init {
        //创建 OKHttpClient
        val builder = OkHttpClient.Builder()
        builder.apply {
            connectTimeout(DEFAULT_TIME_OUT, TimeUnit.SECONDS) //连接超时时间
            writeTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS) //写操作 超时时间
            readTimeout(DEFAULT_READ_TIME_OUT, TimeUnit.SECONDS) //读操作超时时间
        }

        //添加公共参数拦截器
        val commonInterceptor = with(HttpCommonInterceptor()) {
            //                       在此处添加一些公共的参数
//            addHeaderParams("ss","sss")
            build()
        }
        builder.addInterceptor(commonInterceptor)

        mRetrofit = with(Retrofit.Builder()) {
            client(builder.build())
            addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            addConverterFactory(GsonConverterFactory.create())
            baseUrl(ApiConfig.BASE_URL)
            build()
        }
    }


    object SingleHolder {
        internal val INSTANCE = RetrofitServiceManager()
    }

    /**
     * 获取对应的Service
     * @param service Service 的 class
     * @param <T>
     */
    fun <T> creat(service: Class<T>): T {
        return mRetrofit.create(service)
    }


}
 