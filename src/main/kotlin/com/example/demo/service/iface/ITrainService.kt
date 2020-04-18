package com.example.demo.service.iface

/**
 * created by chenyang
 * on 2020/2/21
 */
interface ITrainService {
    fun addAbsent(username: String, time: Long, absentProject: String): String
    fun addLeave(username: String, time: Long, leaveProject: String, leaveReason: String): String
    fun getPersonalAbsents(username: String): String
    fun getPersonalLeaves(username: String): String
}