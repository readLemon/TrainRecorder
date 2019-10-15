package com.example.train.interfaces

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 19-10-13
 */
interface AddCountService {

    @FormUrlEncoded
    @POST("/addCount")
    fun addCount(@Field("name") name: String): Call<String>
}