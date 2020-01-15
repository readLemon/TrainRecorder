package com.example.train.repository

import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.train.model.TeamAbsentModel
import com.example.train.interfaces.OnInternetCallback
import com.example.train.model.PersonalAbsentModel
import com.example.train.model.TeamCountModel
import com.example.train.retrofit.getAll
import com.example.train.retrofit.getCount
import retrofit2.Response

class CheckAbsentRepository(application: Application) {

    val allTeamMemberAbsentLive = MutableLiveData<TeamAbsentModel>()
    val teamCountLive = MutableLiveData<TeamCountModel>()
    val personalAbsentLive = MutableLiveData<PersonalAbsentModel>()

    fun getTeamAbsentData() {
        getAll(object : OnInternetCallback<TeamAbsentModel> {
            override fun onFailed() {

            }

            override fun onSuccessful(response: Response<TeamAbsentModel>) {
                allTeamMemberAbsentLive.value = response.body()
            }

        })
    }

    fun getTeamCount(){
        //团队的训练次数
        com.example.train.retrofit.getTeamCount(object : OnInternetCallback<TeamCountModel> {
            override fun onFailed() {

            }

            override fun onSuccessful(response: Response<TeamCountModel>) {
                teamCountLive.value = response.body()
            }

        })
    }

    fun getPersonalAbsent(name: String) {
        getCount(name, object : OnInternetCallback<PersonalAbsentModel>{
            override fun onFailed() {
            }

            override fun onSuccessful(response: Response<PersonalAbsentModel>) {
                personalAbsentLive.value = response.body()
            }

        })
    }


}