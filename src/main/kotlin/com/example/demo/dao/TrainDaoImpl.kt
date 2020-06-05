package com.example.demo.dao

import com.example.demo.bean.Absent
import com.example.demo.bean.Leave
import com.example.demo.dao.iface.ITrainDao
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.text.SimpleDateFormat

/**
 * created by chenyang
 * on 2020/4/18
 */

@Repository
class TrainDaoImpl: BaseDao(), ITrainDao {

    override fun addAbsent(username: String, time: Long, absentProject: String): Boolean {
        val insertSql = "insert into p_absent(name, time, project) values (:name, :time, :project)"
        val params = hashMapOf<String, Any>("name" to username, "time" to time, "project" to absentProject)
        val result = namedParameterJdbcTemplate.update(insertSql, params)

        return result==1
    }

    override fun addLeave(username: String, time: Long, leaveProject: String, leaveReason: String): Boolean {
        val insertSql = "insert into p_leave(name, time, project, reason) values (:name, :time, :project, :reason)"
        val params = hashMapOf<String, Any>("name" to username, "time" to time, "project" to leaveProject, "reason" to leaveReason)
        val result = namedParameterJdbcTemplate.update(insertSql, params)
        return result == 1
    }

    override fun getPersonalAbsents(username: String): List<Absent> {
        val sql = "select * from p_absent where name=:username"
        val param = hashMapOf<String, Any>("username" to username)
        val absents = namedParameterJdbcTemplate.queryForList(sql,param)

        val result = mutableListOf<Absent>()

        for (tmp in absents) {
            val tmp1 = tmp.get("time") as Long

            val tmp2 = tmp.get("project") as String

            val t = Absent(tmp1,tmp2)
            result.add(t)
        }

        return result
    }

    override fun getPersonalLeaves(username: String): List<Leave> {
        val sql = "select * from p_leave where name = :username"
        val param = hashMapOf<String, Any>("username" to username)
        val leaves = namedParameterJdbcTemplate.queryForList(sql,param)
        val result = mutableListOf<Leave>()
        for (tmp in leaves) {
            val tmp1 = tmp.get("time") as Long

            val tmp2 = tmp.get("project")
            val tmp3 = tmp.get("reason")

            val t = Leave(tmp1, tmp2 as String, tmp3 as String)
            result.add(t)
        }
        return result
    }
}