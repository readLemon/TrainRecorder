package com.example.train.retrofit.loader


import com.example.train.model.BaseResponse
import com.example.train.model.TeamAbsentModel
import com.example.train.model.TeamCountModel
import com.example.train.retrofit.interfaces.TeamService
import io.reactivex.Observable

/**
 * 用于加载一些团队的数据
 * Created by chenyang
 * on 20-1-17
 */
class TeamLoader : BaseLoader<TeamService>() {

    override val clazz: Class<TeamService>
        get() = TeamService::class.java

    fun getTeamTrainCount(): Observable<List<TeamCountModel.Data>> {
        return observe(service.getTeamCount()).map({
            it.data
        })
    }

   /**
    * @param null
    * @return
    */
    fun getTeamAbsentList(): Observable<List<TeamAbsentModel.Data>> {
        return observe(service.getAllAbsentMember().map {
            it.data
        })
    }

    /**
     * 增加团队成员
     * @param null
     * @return
     */
    fun requestAddUser(name: String): Observable<BaseResponse> {
        return observe(service.requestAddUser(name))
    }

    fun requestAddTeamTrainCount(teamName: String): Observable<BaseResponse> {
        return observe(service.addTrainCount(teamName))
    }


}