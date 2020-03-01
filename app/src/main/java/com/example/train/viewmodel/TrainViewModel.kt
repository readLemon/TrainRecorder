package com.example.train.viewmodel

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.bean.PAbsentBean
import com.example.train.bean.PLeaveBean
import com.example.train.repository.TrainRepo

/**
 * Created by chenyang
 * on 20-1-17
 */
class TrainViewModel: ViewModel() {

    private val mTrainRepo: TrainRepo

    init {
        mTrainRepo = TrainRepo()
    }

    fun addAbsent(username: String, time: Long, project: String) = mTrainRepo.addAbsent(username, time, project)


    fun addLeave(username: String, time: Long, project: String, reason: String) = mTrainRepo.addLeave(username, time, project, reason)

    fun getAbsents(username: String): MutableLiveData<PAbsentBean> {
        return mTrainRepo.getAbsents(username)
    }

    fun getLeaves(username: String): MutableLiveData<PLeaveBean> {
        return mTrainRepo.getLeaves(username)
    }



}