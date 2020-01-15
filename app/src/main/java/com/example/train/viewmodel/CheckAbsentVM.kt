package com.example.train.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.model.PersonalAbsentModel
import com.example.train.model.TeamAbsentModel
import com.example.train.model.TeamCountModel
import com.example.train.repository.CheckAbsentRepository

class CheckAbsentVM(application: Application) : ViewModel() {

    private val repository: CheckAbsentRepository

    val teamAbsentData: MutableLiveData<TeamAbsentModel>
    val teamCount: MutableLiveData<TeamCountModel>
    val personalAbsent: MutableLiveData<PersonalAbsentModel>

    init {
        repository = CheckAbsentRepository(application)
        teamAbsentData = repository.allTeamMemberAbsentLive
        teamCount = repository.teamCountLive
        personalAbsent = repository.personalAbsentLive
    }

    fun requestData() {
        repository.getTeamAbsentData()
        repository.getTeamCount()
    }

    fun requestPersonalData(name: String) {
        repository.getPersonalAbsent(name)
    }


}