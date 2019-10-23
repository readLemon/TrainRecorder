package com.example.train.interfaces

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 19-10-13
 */
interface AddAbsentService {

    @FormUrlEncoded
    @POST("/addAbsent")
    fun addAbsent(@FieldMap data: Map<String, String>): Call<String>
}