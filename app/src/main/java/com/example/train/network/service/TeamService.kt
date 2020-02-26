package com.example.train.network.service

import com.example.train.bean.BaseResponse
import com.example.train.bean.JsonWrapper
import com.example.train.bean.TeamAbsentBean
import com.example.train.bean.TeamCountBean
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

    @GET("getTeamCount")
    fun getTeamCount(): Observable<JsonWrapper<TeamCountBean>>


    @GET("getAll")
    fun getAllAbsentMember(): Observable<JsonWrapper<TeamAbsentBean>>

    @FormUrlEncoded
    @POST("/addUser")
    fun requestAddUser(@Field("name") name: String): Observable<JsonWrapper<*>>


    @FormUrlEncoded
    @POST("/addTeamCount")
    fun addTrainCount(@Field("name") teamName: String): Observable<JsonWrapper<*>>

}