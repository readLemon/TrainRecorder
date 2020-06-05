package com.example.demo.dao

import com.example.demo.bean.TeamTrainData
import com.example.demo.dao.iface.ITeamDao
import org.springframework.stereotype.Repository
import java.sql.Timestamp
import java.text.SimpleDateFormat
import java.util.*

/**
 * created by chenyang
 * on 2020/4/18
 */
@Repository
class TeamDaoImpl : ITeamDao, BaseDao() {
    override fun getTeamData(teamName: String): List<TeamTrainData> {
        val selectSql = "SELECT * FROM team_data WHERE name=:teamName"

        val params = hashMapOf<String, Any>("teamName" to teamName)
        val teamTrainDatas = namedParameterJdbcTemplate.queryForList(selectSql, params)
        val result = mutableListOf<TeamTrainData>()
        for (tmp in teamTrainDatas) {
            val projrct = tmp.get("project") as String
            val time = tmp.get("time") as Long

            val t = TeamTrainData(projrct,time)
            result.add(t)
        }

        return result
    }

    override fun addTeamData(teamName: String, project: String, time: Long): Boolean {
        val insertSql = "insert into team_data(name, project, time) values(:teamname, :project, :time)"

        val params = hashMapOf<String, Any>("teamname" to teamName, "project" to project, "time" to time)
        val result = namedParameterJdbcTemplate.update(insertSql, params)

        return result == 1
    }

}