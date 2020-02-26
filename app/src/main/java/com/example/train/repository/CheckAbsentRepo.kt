package com.example.train.repository

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.MutableLiveData
import com.example.train.bean.PAbsentBean
import com.example.train.bean.TeamDataBean
import com.example.train.network.Fault
import com.example.train.network.loader.TrainLoader
import com.example.train.network.loader.TeamLoader
import io.reactivex.functions.Consumer
@SuppressLint("CheckResult")
class CheckAbsentRepo(application: Application) {


    private val mTeamLoader: TeamLoader
    private val mTrainLoader: TrainLoader

    init {
        mTeamLoader = TeamLoader()
        mTrainLoader = TrainLoader()
    }

    fun getTeamTrainData(teamName: String): MutableLiveData<TeamDataBean> {
        val trainData = MutableLiveData<TeamDataBean>()
        mTeamLoader.getTeamTrainData(teamName).subscribe(
            object : Consumer<TeamDataBean> {
                override fun accept(t: TeamDataBean) {
                    trainData.value = t
                }
            },
            object : Consumer<Throwable> {
                override fun accept(t: Throwable) {

                }

            }
        )
        return trainData

    }


    fun getPersonalAbsent(name: String): MutableLiveData<List<PAbsentBean.Absent>> {
        val data = MutableLiveData<List<PAbsentBean.Absent>>()
        mTrainLoader.getAbsentList(name).subscribe(
            { t ->
                data.value = t.absents
            },
            { t ->
                if (t is Fault) {

                }
            })

        return data
    }


}