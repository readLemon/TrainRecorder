package com.example.demo.dao.iface

import com.example.demo.bean.Absent
import com.example.demo.bean.Leave
import java.sql.SQLException

/**
 * created by chenyang
 * on 2020/2/21
 */
interface ITrainDao {
    fun addAbsent(username: String, time: Long, absentProject: String): Boolean
    fun addLeave(username: String, time: Long, leaveProject: String, leaveReason: String): Boolean

    @Throws(SQLException::class)
    fun getPersonalAbsents(username: String): List<Absent>

    @Throws(SQLException::class)
    fun getPersonalLeaves(username: String): List<Leave>
}