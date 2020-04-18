package com.example.demo.service

import com.example.demo.bean.ApiConfig
import com.example.demo.bean.Result
import com.example.demo.bean.TeamResult
import com.example.demo.dao.TeamDaoImpl
import com.example.demo.service.iface.ITeamService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException

/**
 * created by chenyang
 * on 2020/2/26
 */
@Service
class TeamServiceImpl : ITeamService {

    @Autowired
    private lateinit var teamDao: TeamDaoImpl

    override fun getTeamData(teamName: String): String {
        val result = Result()
        var teamResult: TeamResult? = null
        try {
            teamResult = TeamResult(
                    teamName = teamName,
                    trainData = teamDao.getTeamData(teamName),
                    totalTrainCount = 0
            )

            result.status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
            result.info = ApiConfig.TeamInfo.GET_DATA_SUCCESSFUL
        } catch (e: SQLException) {
            result.status = ApiConfig.ResponseStatus.GET_TEAM_DATA_FAILED
            result.info = ApiConfig.TeamInfo.GET_DATA_FAILED
            e.printStackTrace()
        } finally {
            result.data = teamResult
        }
        return result.toJson()
    }

    override fun addTeamData(teamName: String, project: String, time: Long): String {
        val result = Result()
        val isSuccess: Boolean = teamDao.addTeamData(teamName, project, time)
        if (isSuccess) {
            result.status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
            result.info = ApiConfig.TeamInfo.ADD_DATA_SUCCESSFUL
        } else {
            result.status = ApiConfig.ResponseStatus.ADD_TEAM_DATA_FAILED
            result.info = ApiConfig.TeamInfo.ADD_DATA_FAILED
        }
        return result.toJson()
    }
}