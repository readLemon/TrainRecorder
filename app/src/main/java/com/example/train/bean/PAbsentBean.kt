package com.example.train.bean

/**
 * 个人的缺勤记录
 * Created by chenyang
 * on 19-10-19d
 */
data class PAbsentBean(
    val absents: List<Absent>,
    val age: Int,
    val leaves: List<Any>,
    val name: String
){
    data class Absent(
        val absentProject: String,
        val absentTime: Long
    )
}
