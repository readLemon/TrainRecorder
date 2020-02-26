package com.example.train.network.service

import com.example.train.bean.JsonWrapper
import com.example.train.bean.UserBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-2-26
 */
interface UserService {

    @FormUrlEncoded
    @POST("/login")
    fun login(@Field("username") username: String,
              @Field("password") psw: String): Observable<JsonWrapper<UserBean>>

    @FormUrlEncoded
    @POST("/register")
    fun register(@Field("username") username: String,
                 @Field("password") psw: String): Observable<JsonWrapper<*>>

}