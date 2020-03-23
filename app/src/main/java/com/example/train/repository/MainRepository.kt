package com.example.train.repository

import com.example.train.bean.Member
import com.example.train.db.entity.TeamMemberEntity
import com.example.train.model.MainModel
import com.example.train.network.JsonWrapperFunc
import com.example.train.network.RetrofitServiceManager
import com.example.train.network.service.MainService
import com.example.train.util.UserUtil
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-3-23
 */
class MainRepository : BaseRepository() {

    private val service by lazy { RetrofitServiceManager.getInstance().creat(MainService::class.java) }
    private val model by lazy { MainModel() }

    fun getMembers(): Observable<MutableList<Member>>? {

        var o: Observable<MutableList<Member>>? = null
        val user = UserUtil.getCurrentUser()

        if (user != null) {
            o = observe(service.getTeamMembers(user.team)).map(JsonWrapperFunc())
        }
        return o
    }


    fun saveMembers(teamMemberEntity: TeamMemberEntity) {
        model.saveUnsignedMember(teamMemberEntity)
    }

}