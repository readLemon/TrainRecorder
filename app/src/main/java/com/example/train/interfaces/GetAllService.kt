package com.example.train.interfaces

import com.example.train.Bean.AbsentListBean
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * 得到缺勤的所有的人员名单
 * Created by chenyang
 * on 19-10-11
 */
interface GetAllService {

    @GET("/getAlll")
    fun getAll(@Query("name") name: String): Call<AbsentListBean>
}