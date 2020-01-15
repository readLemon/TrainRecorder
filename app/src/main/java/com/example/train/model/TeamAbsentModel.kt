package com.example.train.model

/**
 * 团队的缺勤名单
 * Created by chenyang
 * on 19-10-22
 */
data class TeamAbsentModel(
    val `data`: List<Data>,
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
