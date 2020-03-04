package com.example.train.network.service

import com.example.train.bean.JsonWrapper
import com.example.train.bean.AbsentBean
import com.example.train.bean.LeaveBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-1-17
 */
interface TrainService {

//    @FormUrlEncoded
//    @POST("/register")
//    fun register(@Field("username") username: String,
//                 @Field("password") psw: String): Boolean
//
//
//    @FormUrlEncoded
//    @POST("/login")
//    fun login(@Field("username") username: String,
//              @Field("password") psw: String): Boolean


    @FormUrlEncoded
    @POST("/addAbsent")
    fun requestAddAbsent(@Field("username") username: String,
                         @Field("time") time: Long,
                         @Field("project") project: String): Observable<JsonWrapper<*>>

    @FormUrlEncoded
    @POST("/addLeave")
    fun requestAddLeave(@Field("username") username: String,
                         @Field("time") time: Long,
                         @Field("project") project: String,
                        @Field("reason") reason: String): Observable<JsonWrapper<*>>



}