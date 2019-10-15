package com.example.train.interfaces

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 增加全队的训练次数
 * Created by chenyang
 * on 19-10-15
 */
interface AddTeamTrainCountService {

    @FormUrlEncoded
    @POST("/addTeamTrainCount")
    fun addTrainCount(@Field("teamName") teamName: String): Call<String>

}