package com.example.demo.dao

import com.example.demo.bean.Absent
import com.example.demo.bean.Leave
import com.example.demo.dao.iface.ITrainDao
import org.springframework.stereotype.Repository

/**
 * created by chenyang
 * on 2020/4/18
 */
@Repository
class TrainDaoImpl: BaseDao(), ITrainDao {

    override fun addAbsent(username: String, time: Long, absentProject: String): Boolean {
        val insertSql = "insert into p_absent(name, time, project) values (:name, :time, :project)"
        val params = hashMapOf<String, Any>("username" to username, "time" to time, "project" to absentProject)
        val result = namedParameterJdbcTemplate.update(insertSql, params)

        return result==1
    }

    override fun addLeave(username: String, time: Long, leaveProject: String, leaveReason: String): Boolean {
        val insertSql = "insert into p_absent(name, time, project, reason) values (:name, :time, :project, :reason)"
        val params = hashMapOf<String, Any>("username" to username, "time" to time, "project" to leaveProject, "reason" to leaveReason)
        val result = namedParameterJdbcTemplate.update(insertSql, params)
        return result == 1
    }

    override fun getPersonalAbsents(username: String): List<Absent> {
        val sql = "select * from p_absent"
        val param = hashMapOf<String, Any>("username" to username)
        val absents = namedParameterJdbcTemplate.queryForList(sql,param,Absent::class.java)
        return absents
    }

    override fun getPersonalLeaves(username: String): List<Leave> {
        val sql = "select * from p_leave"
        val param = hashMapOf<String, Any>("username" to username)
        val leaves = namedParameterJdbcTemplate.queryForList(sql,param,Leave::class.java)
        return leaves
    }
}