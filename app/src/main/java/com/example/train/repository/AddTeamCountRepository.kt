package com.example.train.repository

import android.app.Application
import android.widget.Toast
import com.example.train.interfaces.OnInternetCallback
import com.example.train.retrofit.addAUser
import com.mredrock.cyxbs.common.utils.LogUtils
import retrofit2.Response

class AddTeamCountRepository(application: Application) {



    fun addTeamCount() {
        com.example.train.retrofit.addTeamCount("SCIE", object : OnInternetCallback<String> {
            override fun onFailed() {


            }

            override fun onSuccessful(response: Response<String>) {

                LogUtils.d("****", "" + response.body())

            }

        })
    }

    fun addUser(memberName: String) {

        addAUser(memberName, object :OnInternetCallback<String> {
            override fun onFailed() {

            }

            override fun onSuccessful(response: Response<String>) {

            }

        })
    }



}