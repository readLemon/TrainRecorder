package com.example.train.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.bean.PersonalAbsentBean
import com.example.train.bean.TeamAbsentBean
import com.example.train.bean.TeamCountBean
import com.example.train.repository.CheckAbsentRepository

class CheckAbsentVM(application: Application) : ViewModel() {

    private val mCheckAbsentRepo: CheckAbsentRepository

    val teamAbsentData: MutableLiveData<List<TeamAbsentBean.Data>>
    val teamCount: MutableLiveData<List<TeamCountBean.Data>>
    val personalAbsent: MutableLiveData<PersonalAbsentBean>

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