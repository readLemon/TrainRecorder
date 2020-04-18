package com.example.demo.service.iface

/**
 * created by chenyang
 * on 2020/3/22
 */
interface ILateService {
    fun addLate(username: String, time: Long, duration: Int, project: String, team: String): String
    fun getLates(username: String, team: String): String
}