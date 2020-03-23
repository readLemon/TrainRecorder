package com.example.train.model

import com.example.train.db.entity.TeamMemberEntity
import com.example.train.db.getDatabase

/**
 * Created by chenyang
 * on 20-3-23
 */
class MainModel : BaseModel() {

    private val dao by lazy { getDatabase().mainDao() }


    fun saveUnsignedMember(teamMemberEntity: TeamMemberEntity) {
        mIOExcutor.execute {
            dao.insert(teamMemberEntity)
        }
    }
}