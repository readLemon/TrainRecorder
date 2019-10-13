package com.example.train.interfaces

import com.example.train.Bean.CountBean
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 19-10-10
 */
interface GetCountService {

    @FormUrlEncoded
    @POST("/getCount")
    fun getCount(@Field("name") name: String): Call<CountBean>

}