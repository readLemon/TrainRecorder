package com.example.train.interfaces

import retrofit2.Call
import retrofit2.http.*

/**
 * Created by chenyang
 * on 19-10-9
 */


interface AddUserService {

    @FormUrlEncoded
    @POST("/addUser")
    fun addUser(@Field("name") name: String): Call<String>
}