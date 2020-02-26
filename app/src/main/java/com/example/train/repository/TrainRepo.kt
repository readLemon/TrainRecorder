package com.example.train.repository

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import com.example.train.bean.BaseResponse
import com.example.train.bean.PAbsentBean
import com.example.train.bean.PLeaveBean
import com.example.train.network.Fault
import com.example.train.network.JsonWrapperFunc
import com.example.train.network.loader.TrainLoader
import io.reactivex.functions.Consumer

/**
 * Created by chenyang
 * on 20-1-17
 */
@SuppressLint("CheckResult")
class TrainRepo {

    private val mTrainLoader = TrainLoader()


    fun addAbsent(username: String, time: Long, project: String) {
        mTrainLoader.requestAddAbsent(username, time, project)
    }

    fun addLeave(username: String, time: Long, project: String, reason: String) {
        mTrainLoader.requestAddLeave(username, time, project, reason)
    }

    fun getAbsents(username: String): MutableLiveData<PAbsentBean> {
        val data = MutableLiveData<PAbsentBean>()
        mTrainLoader.getAbsents(username).map(JsonWrapperFunc()).subscribe(
            {pabsents -> data.value = pabsents },
            {throwable -> }
        )
        return data
    }

    fun getLeaves(username: String): MutableLiveData<PLeaveBean> {
        val data = MutableLiveData<PLeaveBean>()
        mTrainLoader.getLeaves(username).map(JsonWrapperFunc()).subscribe(
            { pleave -> data.value = pleave },
            { throwable ->}
        )
        return data
    }

}