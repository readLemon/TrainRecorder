package com.example.train.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.train.repository.AddTeamCountRepository

class AddTeamCountVM(application: Application): ViewModel() {

    private val mAddTeamCountRepo: AddTeamCountRepository

    init {
        mAddTeamCountRepo = AddTeamCountRepository(application)
    }

    fun requestAddTeamCount() {
        mAddTeamCountRepo.addTeamCount()
    }

    fun requestAddUser(username: String){
        mAddTeamCountRepo.addUser(username)
    }


}