package com.example.train.model

/**
 * 个人的缺勤记录
 * Created by chenyang
 * on 19-10-19d
 */

data class PersonalAbsentModel(
    val absent: Int,
    val leave: List<Leave>,
    val leave_times: Int,
    val name: String
){

    data class Leave(
        val project: String,
        val reason: String,
        val time: String
    )

}
