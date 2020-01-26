package com.example.train.network.interfaces

import com.example.train.model.BaseResponse
import com.example.train.model.TeamAbsentModel
import com.example.train.model.TeamCountModel
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
    fun getTeamCount(): Observable<TeamCountModel>


    @GET("getAll")
    fun getAllAbsentMember(): Observable<TeamAbsentModel>

    @FormUrlEncoded
    @POST("/addUser")
    fun requestAddUser(@Field("name") name: String): Observable<BaseResponse>


    @FormUrlEncoded
    @POST("/addTeamCount")
    fun addTrainCount(@Field("name") teamName: String): Observable<BaseResponse>

}