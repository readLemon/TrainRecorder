package com.example.train.network.service

import com.example.train.bean.JsonWrapper
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-3-19
 */
interface TimeService {

    @FormUrlEncoded
    @POST("/addAbsent")
    fun requestAddAbsent(
        @Field("username") username: String,
        @Field("time") time: Long,
        @Field("project") project: String
    ): Observable<JsonWrapper<*>>

    @FormUrlEncoded
    @POST("/addLate")
    fun requestAddLate(
        @Field("username") username: String,
        @Field("time") time: Long,
        @Field("duration") duration: Int,
        @Field("project") project: String
    ): Observable<JsonWrapper<*>>


}