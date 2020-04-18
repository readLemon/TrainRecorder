package com.example.demo.service.iface

/**
 * created by chenyang
 * on 2020/2/26
 */
interface ITeamService {
    fun getTeamData(teamName: String): String
    fun addTeamData(teamName: String, project: String, time: Long): String
}