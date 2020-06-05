package com.example.demo.service

import com.example.demo.bean.ApiConfig
import com.example.demo.bean.Late
import com.example.demo.bean.Result
import com.example.demo.dao.LateDaoImpl
import com.example.demo.util.UserUtil
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import java.sql.SQLException
import javax.annotation.Resource

/**
 * created by chenyang
 * on 2020/3/22
 */
@Service
class LateServiceImpl {

    @Autowired
    lateinit var userUtil: UserUtil

    @Resource
    private lateinit var lateDao: LateDaoImpl
    fun addLate(username: String, time: Long, duration: Int, project: String, team: String): String {
        val result = Result()
        //判断用户是否存在
        if (userUtil.isUserExist(username)) {
            result.status = ApiConfig.ResponseStatus.INVALIB_USER
            result.info = ApiConfig.UserInfo.INVALIB_USER
            return result.toJson()
        }
        val re: Boolean = lateDao.addLate(username, time, duration, project, team)
        if (re) {
            result.status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
            result.info = ApiConfig.LateInfo.ADD_LATES_SUCCESSFUL
        } else {
            result.status = ApiConfig.ResponseStatus.ADD_LATE_FAILED
            result.info = ApiConfig.LateInfo.ADD_LATES_FAILED
        }
        return result.toJson()
    }

    fun getLates(username: String, team: String): String {
        val lates: List<Late>
        val result = Result()
        //判断用户是否存在
        if (userUtil.isUserExist(username)) {
            result.status = ApiConfig.ResponseStatus.INVALIB_USER
            result.info = ApiConfig.UserInfo.INVALIB_USER
            return result.toJson()
        }
        try {
            lates = lateDao.getLates(username, team)
            result.data = lates
            result.status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
            result.info = ApiConfig.LateInfo.GET_LATES_SUCCESSFUL
        } catch (e: SQLException) {
            e.printStackTrace()
            result.info = ApiConfig.LateInfo.GET_LATES_FAILED
            result.status = ApiConfig.ResponseStatus.GET_LATES_FAILED
        }
        return result.toJson()
    }
}