package com.example.train.repository

import androidx.lifecycle.MutableLiveData
import com.example.train.db.entity.TeamMemberEntity
import com.example.train.model.TimeModel

/**
 * Created by chenyang
 * on 20-3-18
 */
class TimeRepository: BaseRepository() {

    private val model by lazy { TimeModel() }

    fun getMembers(): MutableLiveData<List<TeamMemberEntity>> {
        return model.getMembers()
    }

}