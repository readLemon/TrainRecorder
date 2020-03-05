package com.example.train.repository

import com.example.train.bean.JsonWrapper
import com.example.train.network.JsonWrapperFunc
import com.example.train.network.RetrofitServiceManager
import com.example.train.network.service.TeamService
import com.example.train.util.UserUtil
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-3-5
 */
class ControlTeamRepository : BaseRepository() {


    private val dao = RetrofitServiceManager.getInstance().creat(TeamService::class.java)


    fun addLeave(time: Long, project: String, reason: String): Observable<JsonWrapper<*>> {
        return observe(dao.requestAddLeave(UserUtil.currentUsername, time, project, reason))
    }

    fun addAbsent(time: Long, project: String): Observable<JsonWrapper<*>> {
        return observe(dao.requestAddAbsent(UserUtil.currentUsername, time, project))
    }

    fun addTeamCount(project: String, time: Long): Observable<JsonWrapper<*>> {
        val teamName = UserUtil.getCurrentUser()?.name
        if (teamName != null) {
            return observe(dao.addTeamTrainData(teamName ,project, time))
        } else{
            return observe(dao.addTeamTrainData("WrongTeam" ,project, time))
        }
    }

}