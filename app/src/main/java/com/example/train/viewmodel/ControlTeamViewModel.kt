package com.example.train.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.repository.ControlTeamRepository

@SuppressLint("CheckResult")
class ControlTeamViewModel : BaseViewModel() {

    private val controTeamRepository by lazy { ControlTeamRepository() }



    fun addLeave(time: Long, project: String, reason: String): MutableLiveData<Boolean> {
        val isSuccess = MutableLiveData<Boolean>(null)
        val dis = controTeamRepository.addLeave(time, project, reason).subscribe(
            {
                isSuccess.value = true
            },
            {
                isSuccess.value = false
            })
        mCompositeDisposable.add(dis)
        return isSuccess
    }

    fun addAbsent(time: Long, project: String): MutableLiveData<Boolean> {
        val isSuccess = MutableLiveData<Boolean>(null)

        val dis = controTeamRepository.addAbsent(time, project).subscribe(
            {
                isSuccess.value = true
            },
            {
                isSuccess.value = false
            })
        mCompositeDisposable.add(dis)
        return isSuccess
    }

    fun addTeamData(project: String, time: Long): MutableLiveData<Boolean> {
        val isSuccess = MutableLiveData<Boolean>(null)
        val dis = controTeamRepository.addTeamCount(project, time).subscribe({
            isSuccess.value = true
        },
            {
                isSuccess.value = false
            })

        mCompositeDisposable.add(dis)
        return isSuccess
    }

}