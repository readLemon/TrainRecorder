package com.example.train.interfaces

import com.example.train.Bean.PersonalAbsentBean
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 返回一个人的训练记录数据
 * Created by chenyang
 * on 19-10-10
 */
interface GetCountService {

    @FormUrlEncoded
    @POST("/getCount")
    fun getCount(@Field("name") name: String): Call<PersonalAbsentBean>

}