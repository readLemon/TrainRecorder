package com.example.train.bean

/**
 * Created by chenyang
 * on 20-2-26
 */

data class PLeaveBean(
    val absents: List<Any>,
    val age: Int,
    val leaves: List<Leave>,
    val name: String
){
    data class Leave(
        val leaveProject: String,
        val leaveReason: String,
        val leaveTime: Long
    )
}

