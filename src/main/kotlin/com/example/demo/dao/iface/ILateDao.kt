package com.example.demo.dao.iface

import com.example.demo.bean.Late
import java.sql.SQLException

/**
 * created by chenyang
 * on 2020/3/22
 */
interface ILateDao {
    @Throws(SQLException::class)
    fun addLate(username: String, time: Long, duration: Int, project: String, team: String): Boolean

    @Throws(SQLException::class)
    fun getLates(username: String, team: String): List<Late>
}