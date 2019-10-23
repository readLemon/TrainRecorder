package com.example.train.Bean

/**
 * Created by chenyang
 * on 19-10-23
 */
data class TeamCountBean(
    val `data`: List<Data>,
    val info: String,
    val status: Int
){

    data class Data(
        val check_count: Int,
        val id: Int,
        val team_name: String
    )

}
