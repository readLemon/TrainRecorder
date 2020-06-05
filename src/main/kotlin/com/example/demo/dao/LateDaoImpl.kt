package com.example.demo.dao

import com.example.demo.bean.Late
import com.example.demo.dao.iface.ILateDao
import org.springframework.stereotype.Repository

/**
 * created by chenyang
 * on 2020/4/18
 */
@Repository
class LateDaoImpl : BaseDao(), ILateDao {

    override fun addLate(username: String, time: Long, duration: Int, project: String, team: String): Boolean {
        val sql = "insert into p_late(username, time, duration, project) values (:username, :time, :duration, :project)"
        val params = hashMapOf<String, Any>("username" to username, "time" to time, "duration" to duration, "project" to project, "team" to team)
        val result = namedParameterJdbcTemplate.update(sql, params)
        return result == 1
    }

    override fun getLates(username: String, team: String): List<Late> {
        val sql = "select * from p_late where username=:username"
        val params = hashMapOf<String, Any>("username" to username, "team" to team)
        val lates = namedParameterJdbcTemplate.queryForList(sql, params)

        val results = arrayListOf<Late>()

        for (tmp in lates) {
            val duration = tmp.get("duration") as Int
            val time = tmp.get("time") as Long
            val project = tmp.get("project") as String

            val t = Late(duration, time, project)

            results.add(t)
        }

        return results
    }
}