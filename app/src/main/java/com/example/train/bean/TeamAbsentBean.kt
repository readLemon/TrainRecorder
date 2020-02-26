package com.example.train.bean

/**
 * 团队的缺勤名单
 * Created by chenyang
 * on 19-10-22
 */
data class TeamAbsentBean(
    val data: Data,
    val info: String,
    val status: Int
){

    data class Data(
        val absent: Int,
        val id: Int,
        val leave_times: Int,
        val name: String
    )

}
