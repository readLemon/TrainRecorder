package com.example.train.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.bean.UserBean
import com.example.train.network.Fault
import com.example.train.repository.LoginRepository
import com.example.train.util.UserUtil
import com.mredrock.cyxbs.common.utils.LogUtil

/**
 * Created by chenyang
 * on 20-3-5
 */
@SuppressLint("CheckResult")
class LoginViewModel : BaseViewModel() {

    private val loginRepo by lazy { LoginRepository() }
    val isLoginSuccess by lazy { MutableLiveData<Boolean>(null) }

    companion object {
        private const val TAG = "LoginViewModel"
    }

    fun login(username: String, psw: String) {
        val dis = loginRepo.login(username, psw).subscribe(
            {
                isLoginSuccess.value = true
                val user = UserBean(it.age, it.name, it.team, it.isCaptaion)
                UserUtil.replaceUser(user)
            },
            {
                if (it is Fault) {
                    LogUtil.e(TAG, "there is a error!!!stasus: ${it.status},info: ${it.info}")
                }
            }
        )

        mCompositeDisposable.add(dis)
    }

    fun register(username: String, psw: String) {
        val dis = loginRepo.resigter(username, psw).subscribe({},
            {

            })
        mCompositeDisposable.add(dis)

    }

}