package com.example.train.model

import androidx.lifecycle.MutableLiveData
import com.example.train.db.entity.TeamMemberEntity
import com.example.train.db.getDatabase
import java.lang.reflect.Member

/**
 * Created by chenyang
 * on 20-3-18
 */
class TimeModel {

    private val db by lazy { getDatabase().MemberDao() }

    fun getMembers():MutableLiveData<List<TeamMemberEntity>> {
        return db.getAll()
    }

}