package com.example.demo.dao.iface

import com.example.demo.bean.TeamTrainData
import java.sql.SQLException

/**
 * created by chenyang
 * on 2020/2/26
 */
interface ITeamDao {
    @Throws(SQLException::class)
    fun getTeamData(teamName: String): List<TeamTrainData>
    fun addTeamData(teamName: String, project: String, time: Long): Boolean
}