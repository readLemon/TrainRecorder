package com.example.train.network.interfaces

import com.example.train.model.BaseResponse
import com.example.train.model.PersonalAbsentModel
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FieldMap
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

/**
 * Created by chenyang
 * on 20-1-17
 */
interface PersonalService {

    @FormUrlEncoded
    @POST("/getCount")
    fun getCount(@Field("name") name: String): Observable<PersonalAbsentModel>


    @FormUrlEncoded
    @POST("/addAbsent")
    fun requestAddAbsent(@FieldMap data: Map<String, String>): Observable<BaseResponse>
}