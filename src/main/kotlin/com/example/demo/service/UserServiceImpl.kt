package com.example.demo.service

import com.example.demo.bean.ApiConfig
import com.example.demo.bean.Result
import com.example.demo.bean.User
import com.example.demo.dao.UserDaoImpl
import com.example.demo.service.iface.IUserService
import com.example.demo.util.isUserExist
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

@Service(value = "userService")
class UserServiceImpl : IUserService {
    @Autowired
    lateinit var userDao: UserDaoImpl
    override fun register(username: String, psw: String): String {
        val re: String = userDao.register(username, psw)
        val result = Result()
        if (re == result.info) {
            result.status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
            result.info = ApiConfig.UserInfo.REGISER_SUCCESSFULL
        } else {
            result.status = ApiConfig.ResponseStatus.REQUEST_FAILED
            result.info = ApiConfig.UserInfo.REGISER_FAILED
        }
        return Result.toJson(result)
    }

    override fun login(username: String, psw: String, code: String): String {
        val userResult: User = userDao.login(username, psw)
        val result = Result()
        if (isUserExist(username)) {
            result.status = ApiConfig.ResponseStatus.INVALIB_USER
            result.info = ApiConfig.UserInfo.INVALIB_USER
            return result.toJson()
        }
        //        GetOpenId getOpenId = new GetOpenId(code);
//        userResult.setOpenId();
        if (userResult.psw.equals(psw)) {
            result.data = userResult
            result.status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
            result.info = ApiConfig.UserInfo.LOGIN_SUCCESSFULL
        } else {
            result.status = ApiConfig.ResponseStatus.REQUEST_FAILED
            result.info = ApiConfig.UserInfo.LOGIN_FAILED
        }
        return Result.toJson(result)
    }
}