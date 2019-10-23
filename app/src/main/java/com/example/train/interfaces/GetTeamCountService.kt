package com.example.train.interfaces

import com.example.train.Bean.TeamCountBean
import retrofit2.Call
import retrofit2.http.*

/**
 * 得到全队总的训练次数
 * Created by chenyang
 * on 19-10-15
 */
interface GetTeamCountService {

    @GET("getTeamCount")
    fun getTeamCount(): Call<TeamCountBean>

}