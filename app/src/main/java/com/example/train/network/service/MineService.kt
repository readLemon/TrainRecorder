package com.example.train.network.service

import com.example.train.bean.AbsentBean
import com.example.train.bean.JsonWrapper
import com.example.train.bean.LeaveBean
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-3-4
 */
interface MineService {

    @FormUrlEncoded
    @POST("/getAbsents")
    fun getAbsents(@Field("username")uesername: String): Observable<JsonWrapper<MutableList<AbsentBean>>>

    @FormUrlEncoded
    @POST("/getLeaves")
    fun getLeaves(@Field("username")username: String): Observable<JsonWrapper<MutableList<LeaveBean>>>
}