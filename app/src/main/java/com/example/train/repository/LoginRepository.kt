package com.example.train.repository

import com.example.train.bean.JsonWrapper
import com.example.train.bean.UserBean
import com.example.train.network.JsonWrapperFunc
import com.example.train.network.RetrofitServiceManager
import com.example.train.network.service.LoginService
import io.reactivex.Observable

/**
 * Created by chenyang
 * on 20-3-5
 */
class LoginRepository: BaseRepository() {

    private val dao = RetrofitServiceManager.getInstance().creat(LoginService::class.java)


    fun login(username: String, psw: String): Observable<UserBean> {
        return observe(dao.login(username, psw)).map(JsonWrapperFunc())
    }

    fun resigter(username: String, psw: String): Observable<JsonWrapper<*>> {
        return observe(dao.register(username, psw))
    }

}