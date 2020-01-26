package com.example.train.repository

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.train.model.PersonalAbsentModel
import com.example.train.model.TeamAbsentModel
import com.example.train.model.TeamCountModel
import com.example.train.network.loader.PersonalLoader
import com.example.train.network.loader.TeamLoader
import com.mredrock.cyxbs.common.utils.LogUtils
import io.reactivex.functions.Consumer

@SuppressLint("CheckResult")
class CheckAbsentRepository(application: Application) {

    val allTeamMemberAbsentLive = MutableLiveData<List<TeamAbsentModel.Data>>()
    val teamCountLive = MutableLiveData<List<TeamCountModel.Data>>()
    val personalAbsentLive = MutableLiveData<PersonalAbsentModel>()
    private val mTeamLoader: TeamLoader
    private val mPersonalLoader: PersonalLoader

    init {
        mTeamLoader = TeamLoader()
        mPersonalLoader = PersonalLoader()
    }
    fun getTeamAbsentData() {
        mTeamLoader.getTeamAbsentList().subscribe(
            object : Consumer<List<TeamAbsentModel.Data>> {
                override fun accept(t: List<TeamAbsentModel.Data>) {
                    allTeamMemberAbsentLive.value = t
                }
            },
            object : Consumer<Throwable> {
                override fun accept(t: Throwable) {
                    LogUtils.e("CheckAbsentRepository", t.message.toString())
                }
            })

    }

    fun getTeamCount() {
        //团队的训练次数

        mTeamLoader.getTeamTrainCount().subscribe(object : Consumer<List<TeamCountModel.Data>> {
            override fun accept(t: List<TeamCountModel.Data>) {
                teamCountLive.value = t
            }
        },
            object : Consumer<Throwable> {
                override fun accept(t: Throwable) {
                    LogUtils.e("CheckAbsentRepository", t.message.toString())
                }

            })


    }

    fun getPersonalAbsent(name: String) {
        mPersonalLoader.getAbsentList(name).subscribe(object : Consumer<PersonalAbsentModel> {
            override fun accept(t: PersonalAbsentModel) {
                personalAbsentLive.value = t
            }
        },
            object : Consumer<Throwable> {
                override fun accept(t: Throwable) {
                    LogUtils.e("CheckAbsentRepository", t.message.toString())
                }

            })
    }


}