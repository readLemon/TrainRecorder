package com.example.train.repository

import com.example.train.bean.AbsentBean
import com.example.train.bean.LeaveBean
import com.example.train.network.JsonWrapperFunc
import com.example.train.network.RetrofitServiceManager
import com.example.train.network.service.MineService
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-3-5
 */
class MineFragmentRepository : BaseRepository() {

    val dao = RetrofitServiceManager.getInstance().creat(MineService::class.java)

    fun getLeaves(username: String): Observable<MutableList<LeaveBean>> {
        return observe(dao.getLeaves(username)).map(JsonWrapperFunc())
    }

    fun getAbsents(username: String): Observable<MutableList<AbsentBean>> {
        return observe(dao.getAbsents(username)).map(JsonWrapperFunc())
    }
}