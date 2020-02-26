package com.example.train.network.loader


import com.example.train.bean.BaseResponse
import com.example.train.bean.TeamAbsentBean
import com.example.train.bean.TeamCountBean
import com.example.train.network.JsonWrapperFunc
import com.example.train.network.RetrofitServiceManager
import com.example.train.network.service.TeamService
import io.reactivex.Observable

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

    fun getTeamTrainCount() = observe(service.getTeamCount()).map(JsonWrapperFunc())

    /**
     * @param null
     * @return
     */
    fun getTeamAbsentList() =  observe(service.getAllAbsentMember()).map(JsonWrapperFunc())


    /**
     * 增加团队成员
     * @param null
     * @return
     */
    fun requestAddUser(name: String)= observe(service.requestAddUser(name))


    fun requestAddTeamTrainCount(teamName: String) = observe(service.addTrainCount(teamName))

}