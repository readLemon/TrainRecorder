package com.example.train.viewmodel

import androidx.lifecycle.LiveData
import com.example.train.bean.Member
import com.example.train.db.entity.TeamMemberEntity
import com.example.train.db.entity.UnsignedEntity
import com.example.train.repository.TimeRepository

/**
 * Created by chenyang
 * on 20-3-18
 */
class TimeViewModel : BaseViewModel() {

    private val repository by lazy { TimeRepository() }


    fun getMembersFromDB(): LiveData<List<TeamMemberEntity>> {
        return repository.getMembers()
    }

    fun saveUnsignedMember(entity: UnsignedEntity) {
        repository.saveUnsignedMember(entity)
    }

    fun saveUnsignedMember(members: List<Member>) {
        var entity: UnsignedEntity
        for (m in members) {
            entity = UnsignedEntity(m.name)
            saveUnsignedMember(entity)
        }
    }

    fun getAllUnsignedMemer(): LiveData<List<UnsignedEntity>> {
        return repository.getAllUnsignedMember()
    }

    fun deleteAllUnsignedMember() {
        repository.deleteAllUnsignedMember()
    }

    fun addLate(time: Long, duration: Int, project: String) {
        val dis = repository
            .addALate(time = time, duration = duration, project = project)
            .subscribe(
                {},
                {})
        mCompositeDisposable.add(dis)
    }

    fun addAbsent(time: Long, project: String) {
        val dis = repository
            .addAbsent(time = time, project = project)
            .subscribe({}, {})
        mCompositeDisposable.add(dis)
    }

}