package com.example.train.Bean

/**
 * Created by chenyang
 * on 19-10-19d
 */

data class PersonalAbsentBean(
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
