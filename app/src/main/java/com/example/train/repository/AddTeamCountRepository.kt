package com.example.train.repository

import android.annotation.SuppressLint
import android.app.Application
import com.example.train.model.BaseResponse
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
                    LogUtils.e("CheckAbsentRepository", t.message.toString())
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
                    LogUtils.e("CheckAbsentRepository", t.message.toString())
                }
            })
    }


}