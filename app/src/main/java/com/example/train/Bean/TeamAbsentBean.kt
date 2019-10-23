package com.example.train.Bean

/**
 * Created by chenyang
 * on 19-10-22
 */
data class TeamAbsentBean(
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
