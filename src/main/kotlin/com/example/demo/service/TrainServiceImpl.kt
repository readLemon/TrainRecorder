package com.example.demo.service

import com.example.demo.bean.Absent
import com.example.demo.bean.ApiConfig
import com.example.demo.bean.Leave
import com.example.demo.bean.Result
import com.example.demo.dao.TrainDaoImpl
import com.example.demo.service.iface.ITrainService
import com.example.demo.util.UserUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException
import javax.annotation.Resource

/**
 * created by chenyang
 * on 2020/2/22
 */
@Service
class TrainServiceImpl : ITrainService {

    @Autowired
    lateinit var userUtil: UserUtil

    @Autowired
    lateinit var trainDao: TrainDaoImpl
    override fun addAbsent(username: String, time: Long, absentProject: String): String {
        val result = Result()
        //判断用户是否存在
        if (userUtil.isUserExist(username)) {
            result.status = ApiConfig.ResponseStatus.INVALIB_USER
            result.info = ApiConfig.UserInfo.INVALIB_USER
            return result.toJson()
        }
        val re: Boolean = trainDao.addAbsent(username, time, absentProject)
        if (re) {
            result.status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
            result.info = ApiConfig.TrainInfo.ADD_ABSENT_SUCCESS
        } else {
            result.status = ApiConfig.ResponseStatus.ADD_ABSENT_FAILED
            result.info = ApiConfig.TrainInfo.ADD_ABSENT_FAILED
        }
        return result.toJson()
    }

    override fun addLeave(username: String, time: Long, leaveProject: String, leaveReason: String): String {
        val result = Result()
        //判断用户是否存在
        if (userUtil.isUserExist(username)) {
            result.status = ApiConfig.ResponseStatus.INVALIB_USER
            result.info = ApiConfig.UserInfo.INVALIB_USER
            return result.toJson()
        }
        val re: Boolean = trainDao.addLeave(username, time, leaveProject, leaveReason)
        if (re) {
            result.status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
            result.info = ApiConfig.TrainInfo.ADD_LEAVE_SUCCESS
        } else {
            result.status = ApiConfig.ResponseStatus.ADD_LEAVE_FAILED
            result.info = ApiConfig.TrainInfo.ADD_LEAVE_FAILED
        }
        return result.toJson()
    }

    override fun getPersonalAbsents(username: String): String {
        val absents: List<Absent>
        val result = Result()
        //判断用户是否存在
        if (userUtil.isUserExist(username)) {
            result.status = ApiConfig.ResponseStatus.INVALIB_USER
            result.info = ApiConfig.UserInfo.INVALIB_USER
            return result.toJson()
        }
        try {
            absents = trainDao.getPersonalAbsents(username)
            result.data = absents
            result.status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
            result.info = ApiConfig.TrainInfo.GET_ABSENTS_SUCCESSFUL
        } catch (e: SQLException) {
            e.printStackTrace()
            result.status = ApiConfig.ResponseStatus.GET_ABSENTS_FAILED
            result.info = ApiConfig.TrainInfo.GET_ABSENTS_FAILED
        }
        return result.toJson()
    }

    override fun getPersonalLeaves(username: String): String {
        val leaves: List<Leave>
        val result = Result()
        //判断用户是否存在
        if (userUtil.isUserExist(username)) {
            result.status = ApiConfig.ResponseStatus.INVALIB_USER
            result.info = ApiConfig.UserInfo.INVALIB_USER
            return result.toJson()
        }
        try {
            leaves = trainDao.getPersonalLeaves(username)
            result.data = leaves
            result.status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
            result.info = ApiConfig.TrainInfo.GET_LEAVES_SUCCESSFULL
        } catch (e: SQLException) {
            e.printStackTrace()
            result.status = ApiConfig.ResponseStatus.GET_LEAVES_FAILED
            result.info = ApiConfig.TrainInfo.GET_ABSENTS_FAILED
        }
        return result.toJson()
    }
}