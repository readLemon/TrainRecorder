package com.example.train.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.model.PersonalAbsentModel
import com.example.train.model.TeamAbsentModel
import com.example.train.model.TeamCountModel
import com.example.train.repository.CheckAbsentRepository

class CheckAbsentVM(application: Application) : ViewModel() {

    private val mCheckAbsentRepo: CheckAbsentRepository

    val teamAbsentData: MutableLiveData<List<TeamAbsentModel.Data>>
    val teamCount: MutableLiveData<List<TeamCountModel.Data>>
    val personalAbsent: MutableLiveData<PersonalAbsentModel>

    init {
        mCheckAbsentRepo = CheckAbsentRepository(application)
        teamAbsentData = mCheckAbsentRepo.allTeamMemberAbsentLive
        teamCount = mCheckAbsentRepo.teamCountLive
        personalAbsent = mCheckAbsentRepo.personalAbsentLive
    }

    fun requestData() {
        mCheckAbsentRepo.getTeamAbsentData()
        mCheckAbsentRepo.getTeamCount()
    }

    fun requestPersonalData(name: String) {
        mCheckAbsentRepo.getPersonalAbsent(name)
    }


}