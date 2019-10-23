package com.example.train.interfaces

import com.example.train.Bean.TeamCountBean
import retrofit2.Call
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * 增加全队的训练次数
 * Created by chenyang
 * on 19-10-15
 */
interface AddTeamCountService {

    @FormUrlEncoded
    @POST("/addTeamCount")
    fun addTrainCount(@Field("name") teamName: String): Call<String>

}