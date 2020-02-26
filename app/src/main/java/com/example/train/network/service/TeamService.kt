package com.example.train.network.service

import com.example.train.bean.JsonWrapper
import com.example.train.bean.TeamDataBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.GET
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-1-17
 */
interface TeamService {

    @GET("getTeamData")
    fun getTeamTrainData(@Field("teamName")teamName: String): Observable<JsonWrapper<TeamDataBean>>


    @FormUrlEncoded
    @POST("/addTeamCount")
    fun addTeamTrainData(@Field("teamName") teamName: String,
                         @Field("project") project: String,
                         @Field("time") time: Long): Observable<JsonWrapper<*>>

}