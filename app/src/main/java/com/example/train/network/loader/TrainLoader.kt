package com.example.train.network.loader

import com.example.train.network.JsonWrapperFunc
import com.example.train.network.RetrofitServiceManager
import com.example.train.network.service.TrainService

/**
 * Created by chenyang
 * on 20-1-17
 */
class TrainLoader : BaseLoader() {
    override val clazz: Class<TrainService>
        get() = TrainService::class.java

    val service: TrainService

    init {
        service = RetrofitServiceManager.getInstance().creat(clazz)

    }


    /**
     * 增加个人的缺勤
     * @param null
     * @return
     */
    fun requestAddAbsent(username: String, time: Long, project: String) = observe(service.requestAddAbsent(username, time, project))

    /**
     * 增加个人请假数据
     */
    fun requestAddLeave(username: String, time: Long, project: String, reason: String) = observe(service.requestAddLeave(username, time, project, reason))

    /**
     * 得到个人的所有缺席
     */
    fun getAbsents(username: String) = observe(service.getAbsents(username))

    /**
     * 得到个人所有的请假数据
     */
    fun getLeaves(username: String) = observe(service.getLeaves(username))



}