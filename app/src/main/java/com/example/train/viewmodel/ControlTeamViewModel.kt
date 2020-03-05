package com.example.train.viewmodel

import android.annotation.SuppressLint
import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.train.repository.ControlTeamRepository

@SuppressLint("CheckResult")
class ControlTeamViewModel: ViewModel() {

    private val controTeamRepository by lazy { ControlTeamRepository() }


    fun addLeave( time: Long, project: String, reason: String) {
        controTeamRepository.addLeave(time, project, reason).subscribe({},
            {

            })
    }

    fun addAbsent(time: Long, project: String) {
        controTeamRepository.addAbsent(time, project).subscribe({},
            {

            })
    }

    fun addTeamData(project: String, time: Long) {
        controTeamRepository.addTeamCount(project, time).subscribe({},
            {

            })
    }

}