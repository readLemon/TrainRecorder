package com.example.train.network.service

import com.example.train.bean.JsonWrapper
import com.example.train.bean.Member
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-3-23
 */
interface MainService {

    @FormUrlEncoded
    @POST("/getLeaves")
    fun getTeamMembers(@Field("teamname") teamname: String): Observable<JsonWrapper<MutableList<Member>>>

}