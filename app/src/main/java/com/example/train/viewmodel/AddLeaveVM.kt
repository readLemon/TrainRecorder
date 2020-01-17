package com.example.train.viewmodel

import androidx.lifecycle.ViewModel
import com.example.train.repository.AddLeaveRepository

/**
 * Created by chenyang
 * on 20-1-17
 */
class AddLeaveVM:ViewModel() {

    private val mAddLeaveRepo: AddLeaveRepository

    init {
        mAddLeaveRepo = AddLeaveRepository()
    }

    fun requestAddLeave(map: Map<String, String>){
        mAddLeaveRepo.addLeave(map)
    }

}