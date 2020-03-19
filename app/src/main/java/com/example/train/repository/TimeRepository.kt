package com.example.train.repository

import androidx.lifecycle.LiveData
import com.example.train.bean.JsonWrapper
import com.example.train.db.entity.TeamMemberEntity
import com.example.train.db.entity.UnsignedEntity
import com.example.train.model.TimeModel
import com.example.train.network.RetrofitServiceManager
import com.example.train.network.service.TimeService
import com.example.train.util.UserUtil
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-3-18
 */
class TimeRepository : BaseRepository() {

    private val model by lazy { TimeModel() }
    private val retrofit by lazy {
        RetrofitServiceManager.getInstance().creat(TimeService::class.java)
    }

    fun getMembers(): LiveData<List<TeamMemberEntity>> {
        return model.getMembers()
    }

    fun saveUnsignedMember(entity: UnsignedEntity) {
        model.saveUnsignedMember(entity)
    }

    fun deleteAllUnsignedMember() {
        model.deleteAllUnsigned()
    }

    fun getAllUnsignedMember(): LiveData<List<UnsignedEntity>> {
        return model.getAllUnsignedMemver()
    }


    fun addALate(
        username: String = UserUtil.currentUsername,
        time: Long,
        duration: Int,
        project: String
    ): Observable<JsonWrapper<*>> {
        return observe(retrofit.requestAddLate(username, time, duration, project))
    }

    fun addAbsent(
        username: String = UserUtil.currentUsername,
        time: Long,
        project: String
    ): Observable<JsonWrapper<*>> {
        return observe(retrofit.requestAddAbsent(username, time, project))
    }


}