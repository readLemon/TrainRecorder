package com.example.demo.dao

import com.example.demo.bean.TeamTrainData
import com.example.demo.dao.iface.ITeamDao
import org.springframework.stereotype.Repository

/**
 * created by chenyang
 * on 2020/4/18
 */
@Repository
class TeamDaoImpl : ITeamDao, BaseDao() {
    override fun getTeamData(teamName: String): List<TeamTrainData> {
        val selectSql = "SELECT * FROM team_data WHERE name=:teamName"

        val params = hashMapOf<String, Any>("teamName" to teamName)
        val teamTrainDatas = namedParameterJdbcTemplate.queryForList(selectSql, params, TeamTrainData::class.java)

        return teamTrainDatas
    }

    override fun addTeamData(teamName: String, project: String, time: Long): Boolean {
        val insertSql = "insert into team_data(name, project, time) values(:teamname, :project, :time)"
        val params = hashMapOf<String, Any>("teamname" to teamName, "project" to project, "time" to time)
        val result = namedParameterJdbcTemplate.update(insertSql, params)

        return result == 1
    }

}