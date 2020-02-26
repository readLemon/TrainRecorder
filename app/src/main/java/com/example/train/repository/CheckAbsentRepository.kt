package com.example.train.repository

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.train.bean.PersonalAbsentBean
import com.example.train.bean.TeamAbsentBean
import com.example.train.bean.TeamCountBean
import com.example.train.network.Fault
import com.example.train.network.loader.PersonalLoader
import com.example.train.network.loader.TeamLoader
import io.reactivex.functions.Consumer

@SuppressLint("CheckResult")
class CheckAbsentRepository(application: Application) {

    val allTeamMemberAbsentLive = MutableLiveData<List<TeamAbsentBean.Data>>()
    val teamCountLive = MutableLiveData<List<TeamCountBean.Data>>()
    val personalAbsentLive = MutableLiveData<PersonalAbsentBean>()
    private val mTeamLoader: TeamLoader
    private val mPersonalLoader: PersonalLoader

    init {
        mTeamLoader = TeamLoader()
        mPersonalLoader = PersonalLoader()
    }

    fun getTeamAbsentData() {
        mTeamLoader.getTeamAbsentList().subscribe(
            object : Consumer<TeamAbsentBean> {
                override fun accept(t: TeamAbsentBean?) {

                }
            },
            object : Consumer<Throwable> {
                override fun accept(t: Throwable) {
                    if (t is Fault) {

                    }
                }
            })

    }

    fun getTeamCount() {
        //团队的训练次数

        mTeamLoader.getTeamTrainCount().subscribe(
            { },
            { t ->
                if (t is Fault) {

                }
            })


    }

    fun getPersonalAbsent(name: String) {
        mPersonalLoader.getAbsentList(name).subscribe(
            { t ->  },
            { t ->
                if (t is Fault) {

                }
            })
    }


}