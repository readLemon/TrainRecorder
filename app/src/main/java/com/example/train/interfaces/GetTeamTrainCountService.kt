package com.example.train.interfaces

import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.GET

/**
 * 得到全队总的训练次数
 * Created by chenyang
 * on 19-10-15
 */
interface GetTeamTrainCountService {


    @GET("getTrainCount")
    fun getTrainCount(@Field("teamName") teamName: String): Call<String>

}