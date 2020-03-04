package com.example.train.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.bean.AbsentBean
import com.example.train.bean.LeaveBean
import com.example.train.network.Fault
import com.example.train.repository.MineFragmentRepository
import com.mredrock.cyxbs.common.utils.LogUtils

/**
 * Created by chenyang
 * on 20-3-4
 */
@SuppressLint("CheckResult")
class MineFragmentViewModel : ViewModel() {

    private val mineRepository by lazy { MineFragmentRepository() }
    val leaves by lazy { MutableLiveData<MutableList<LeaveBean>>() }
    val absents by lazy { MutableLiveData<MutableList<AbsentBean>>() }

    companion object {
        private val TAG = "MineFragmentViewModel"
    }

    fun getLeaves(username: String) {
        mineRepository.getLeaves(username).subscribe(
            {
                leaves.value = it
            },
            {
                if (it is Fault) {
                    LogUtils.e(TAG, "there is a error!!!stasus: ${it.status},info: ${it.info}")
                }
            })

    }

    fun getAbsents(username: String) {
        mineRepository.getAbsents(username).subscribe(
            {
                absents.value = it
            },
            {
                if (it is Fault) {
                    LogUtils.e(TAG, "there is a error!!!stasus: ${it.status},info: ${it.info}")
                }
            }
        )
    }

}