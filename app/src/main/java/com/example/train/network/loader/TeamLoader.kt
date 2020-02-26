package com.example.train.network.loader


import com.example.train.network.JsonWrapperFunc
import com.example.train.network.RetrofitServiceManager
import com.example.train.network.service.TeamService

/**
 * 用于加载一些团队的数据
 * Created by chenyang
 * on 20-1-17
 */
class TeamLoader : BaseLoader() {

    override val clazz: Class<TeamService>
        get() = TeamService::class.java

    val service: TeamService

    init {
        service = RetrofitServiceManager.getInstance().creat(clazz)
    }

    fun getTeamTrainData(teamName: String) = observe(service.getTeamTrainData(teamName)).map(JsonWrapperFunc())


    fun requestAddTeamTrainData(teamName: String, project: String, time: Long) = observe(service.addTeamTrainData(teamName, project, time))

}