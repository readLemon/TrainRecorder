package com.example.train.viewmodel

import android.annotation.SuppressLint
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.repository.ControlTeamRepository

@SuppressLint("CheckResult")
class ControlTeamViewModel : ViewModel() {

    private val controTeamRepository by lazy { ControlTeamRepository() }



    fun addLeave(time: Long, project: String, reason: String): MutableLiveData<Boolean> {
        val isSuccess = MutableLiveData<Boolean>(null)
        controTeamRepository.addLeave(time, project, reason).subscribe(
            {
                isSuccess.value = true
            },
            {
                isSuccess.value = false
            })

        return isSuccess
    }

    fun addAbsent(time: Long, project: String): MutableLiveData<Boolean> {
        val isSuccess = MutableLiveData<Boolean>(null)

        controTeamRepository.addAbsent(time, project).subscribe(
            {
                isSuccess.value = true
            },
            {
                isSuccess.value = false
            })

        return isSuccess
    }

    fun addTeamData(project: String, time: Long): MutableLiveData<Boolean> {
        val isSuccess = MutableLiveData<Boolean>(null)
        controTeamRepository.addTeamCount(project, time).subscribe({
            isSuccess.value = true
        },
            {
                isSuccess.value = false
            })

        return isSuccess
    }

}