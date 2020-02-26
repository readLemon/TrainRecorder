package com.example.train.repository

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.train.bean.BaseResponse
import com.example.train.bean.TeamDataBean
import com.example.train.network.Fault
import com.example.train.network.loader.TeamLoader
import io.reactivex.functions.Consumer

@SuppressLint("CheckResult")
class TeamDataRepo(application: Application) {

    private val mTeamLoader: TeamLoader

    init {
        mTeamLoader = TeamLoader()
    }

    fun addTeamData(teamName:String, project: String, time: Long) {
        mTeamLoader.requestAddTeamTrainData(teamName, project, time)
    }

    fun getTeamTrainData(teamName: String): MutableLiveData<TeamDataBean> {
        val data = MutableLiveData<TeamDataBean>()
        mTeamLoader.getTeamTrainData(teamName).subscribe(
            object: Consumer<TeamDataBean>{
                override fun accept(t: TeamDataBean) {
                    data.value = t
                }
            },
            object :Consumer<Throwable> {
                override fun accept(t: Throwable) {

                }

            })

        return data

    }




}