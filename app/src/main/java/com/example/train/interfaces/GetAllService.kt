package com.example.train.interfaces

import com.example.train.model.TeamAbsentModel
import retrofit2.Call
import retrofit2.http.GET

/**
 * 得到缺勤的所有的人员名单
 * Created by chenyang
 * on 19-10-11
 */
interface GetAllService {

    @GET("getAll")
    fun getAll(): Call<TeamAbsentModel>
}