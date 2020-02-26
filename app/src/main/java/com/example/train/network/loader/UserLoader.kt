package com.example.train.network.loader

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.train.bean.UserBean
import com.example.train.network.JsonWrapperFunc
import com.example.train.network.RetrofitServiceManager
import com.example.train.network.service.UserService
import java.util.function.Consumer

/**
 * Created by chenyang
 * on 20-2-26
 */

class UserLoader: BaseLoader() {

    override val clazz: Class<UserService>
        get() = UserService::class.java

   private val service: UserService
    init {
        service = RetrofitServiceManager.getInstance().creat(clazz)
    }

    fun login(username: String, psw: String) = service.login(username, psw)

    fun register(username: String, psw: String) = service.register(username, psw)


}