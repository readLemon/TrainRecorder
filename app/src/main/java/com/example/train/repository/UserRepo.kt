package com.example.train.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.train.bean.UserBean
import com.example.train.network.JsonWrapperFunc
import com.example.train.network.loader.UserLoader

/**
 * Created by chenyang
 * on 20-2-26
 */
@SuppressLint("CheckResult")
class UserRepo {

    private val userLoader = UserLoader()

    fun login(username: String, psw: String): MutableLiveData<UserBean> {
        val data = MutableLiveData<UserBean>()
        userLoader.login(username, psw).map(JsonWrapperFunc()).subscribe(
            {userBean -> data.value = userBean},
            {throwable ->})
        return data
    }

    fun register(username: String, psw: String) {
        userLoader.register(username, psw).subscribe({},{})
    }

}