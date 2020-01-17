package com.example.train.repository

import android.annotation.SuppressLint
import com.example.train.model.BaseResponse
import com.example.train.retrofit.loader.PersonalLoader
import io.reactivex.functions.Consumer

/**
 * Created by chenyang
 * on 20-1-17
 */
@SuppressLint("CheckResult")
class AddLeaveRepository {

    private val mPersonalLoader: PersonalLoader

    init {
        mPersonalLoader = PersonalLoader()
    }

    fun addLeave(map: Map<String, String>) {
        mPersonalLoader.requestAddAbsent(map).subscribe(
            object : Consumer<BaseResponse> {
                override fun accept(t: BaseResponse) {

                }

            },
            object :Consumer<Throwable>{
                override fun accept(t: Throwable?) {

                }
            }
        )
    }

}