package com.example.train.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.train.bean.UserBean
import com.example.train.repository.UserRepo

/**
 * Created by chenyang
 * on 20-2-26
 */
class UserViewModel {

    private val userRepo = UserRepo()

    fun register(username: String, psw: String) {
        userRepo.register(username, psw)
    }

    fun login(username: String, psw: String): MutableLiveData<UserBean> {
        return userRepo.login(username, psw)
    }
}