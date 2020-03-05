package com.example.train.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.bean.UserBean
import com.example.train.network.Fault
import com.example.train.repository.LoginRepository
import com.example.train.util.UserUtil
import com.mredrock.cyxbs.common.utils.LogUtils

/**
 * Created by chenyang
 * on 20-3-5
 */
@SuppressLint("CheckResult")
class LoginViewModel : ViewModel() {

    private val loginRepo by lazy { LoginRepository() }
    val isLoginSuccess by lazy { MutableLiveData<Boolean>(null) }

    companion object {
        private const val TAG = "LoginViewModel"
    }

    fun login(username: String, psw: String) {
        loginRepo.login(username, psw).subscribe(
            {
                isLoginSuccess.value = true
                val user = UserBean(it.age, it.name, it.team)
                UserUtil.replaceUser(user)
            },
            {
                if (it is Fault) {
                    LogUtils.e(TAG, "there is a error!!!stasus: ${it.status},info: ${it.info}")
                }
            }
        )
    }

    fun register(username: String, psw: String) {
        loginRepo.resigter(username, psw).subscribe({},
            {

            })
    }

}