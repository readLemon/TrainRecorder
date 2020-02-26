package com.example.train.viewmodel

import android.app.Application
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.train.bean.TeamDataBean
import com.example.train.repository.TeamDataRepo

class TeamCountVM(application: Application): ViewModel() {

    private val mTeamDataRepo: TeamDataRepo

    init {
        mTeamDataRepo = TeamDataRepo(application)
    }

    fun requestAddTeamData(teamName: String, project: String, time: Long) {
        mTeamDataRepo.addTeamData(teamName, project, time)
    }

    fun requestTeamData(teamName: String): MutableLiveData<TeamDataBean> {
        return mTeamDataRepo.getTeamTrainData(teamName)
    }


}