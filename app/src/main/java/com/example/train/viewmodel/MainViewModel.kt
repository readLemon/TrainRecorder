package com.example.train.viewmodel

import com.example.train.db.entity.TeamMemberEntity
import com.example.train.repository.MainRepository

/**
 * Created by chenyang
 * on 20-3-23
 */
class MainViewModel : BaseViewModel() {

    private val repo by lazy { MainRepository() }


    fun initMembers() {
        val dis = repo.getMembers()?.subscribe(
            {
                for (m in it) {
                    repo.saveMembers(TeamMemberEntity(m.name))
                }
            }, {})
        dis?.let { mCompositeDisposable.add(it) }
    }

}