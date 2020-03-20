package com.example.train.model

import androidx.lifecycle.LiveData
import com.example.train.db.AppExcutors
import com.example.train.db.entity.TeamMemberEntity
import com.example.train.db.entity.UnsignedEntity
import com.example.train.db.getDatabase
import com.mredrock.cyxbs.common.utils.LogUtil

/**
 * Created by chenyang
 * on 20-3-18
 */
class TimeModel: BaseModel() {

    private val memberDao by lazy { getDatabase().MemberDao() }
    private val timeDao by lazy { getDatabase().TimeDao() }

    fun getMembers(): LiveData<List<TeamMemberEntity>> {
        return memberDao.getAll()
    }

    fun saveUnsignedMember(unsignedEntity: UnsignedEntity) {
        mIOExcutor.execute {
            timeDao.insert(unsignedEntity)
        }
    }

    fun deleteAllUnsigned() {
        mIOExcutor.execute {
            timeDao.deleteAll()
        }
    }

    fun getAllUnsignedMemver(): LiveData<List<UnsignedEntity>> {
        return timeDao.getAll()
    }

    companion object{
        const val TAG = "TimeModel"
    }

}