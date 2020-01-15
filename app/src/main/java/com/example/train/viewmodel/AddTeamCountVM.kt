package com.example.train.viewmodel

import android.app.Application
import androidx.lifecycle.ViewModel
import com.example.train.repository.AddTeamCountRepository

class AddTeamCountVM(application: Application): ViewModel() {

    val addTeamCountRepository: AddTeamCountRepository

    init {
        addTeamCountRepository = AddTeamCountRepository(application)
    }

    fun requestAddTeamCount() {
        addTeamCountRepository.addTeamCount()
    }

    fun requestAddUser(username: String){
        addTeamCountRepository.addUser(username)
    }


}