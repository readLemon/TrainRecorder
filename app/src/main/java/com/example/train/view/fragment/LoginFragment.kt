package com.example.train.view.fragment

import android.view.View
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.train.R
import com.example.train.viewmodel.LoginViewModel
import kotlinx.android.synthetic.main.fragment_login.*

/**
 * Created by chenyang
 * on 20-2-27
 */
class LoginFragment : BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_login
    private val viewModel by viewModels<LoginViewModel>()

    override fun initial(view: View) {
        btn_login_fm_login.setOnClickListener {
            val username = et_login_fm_username.text.toString().trim()
            val psw = et_login_fm_psw.text.toString().trim()
            if (isUsernameAvailable(username) && isPasswordAvailable(psw)) {
                viewModel.login(username, psw)
                viewModel.isLoginSuccess.observe(this, Observer {
                    if (it == true) {
                        val action = LoginFragmentDirections.actionLoginFragmentToFragmentMain()
                        findNavController().navigate(action)
                    }
                })
            }
        }
    }

    private fun isUsernameAvailable(username: String): Boolean {
        return username.length == 10
    }

    private fun isPasswordAvailable(psw: String): Boolean {
        return psw.length == 6
    }


    override fun onDestroy() {
        viewModel.clearDisposable()
        super.onDestroy()
    }
}