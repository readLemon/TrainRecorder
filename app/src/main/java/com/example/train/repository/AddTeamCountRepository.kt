package com.example.train.repository

import android.annotation.SuppressLint
import android.app.Application
import com.example.train.bean.BaseResponse
import com.example.train.network.Fault
import com.example.train.network.loader.TeamLoader
import com.mredrock.cyxbs.common.utils.LogUtils
import io.reactivex.functions.Consumer

@SuppressLint("CheckResult")
class AddTeamCountRepository(application: Application) {

    private val mTeamLoader: TeamLoader

    init {
        mTeamLoader = TeamLoader()
    }

    fun addTeamCount() {


        mTeamLoader.requestAddTeamTrainCount("SCIE").subscribe(
            object : Consumer<BaseResponse> {
                override fun accept(t: BaseResponse) {

                }
            },
            object : Consumer<Throwable> {
                override fun accept(t: Throwable) {
                    if (t is Fault){

                    }
                }
            })
    }

    fun addUser(memberName: String) {
        mTeamLoader.requestAddUser(memberName).subscribe(

            object : Consumer<BaseResponse> {
                override fun accept(t: BaseResponse) {

                }
            },
            object : Consumer<Throwable> {
                override fun accept(t: Throwable) {
                    if (t is Fault){

                    }
                }
            })
    }


}