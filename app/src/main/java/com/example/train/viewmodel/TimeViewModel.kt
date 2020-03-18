package com.example.train.viewmodel

import androidx.lifecycle.MutableLiveData
import com.example.train.db.entity.TeamMemberEntity
import com.example.train.repository.TimeRepository

/**
 * Created by chenyang
 * on 20-3-18
 */
class TimeViewModel: BaseViewModel() {

    private val repository by lazy { TimeRepository() }


    fun getMembers(): MutableLiveData<List<TeamMemberEntity>> {
        return repository.getMembers()
    }

}