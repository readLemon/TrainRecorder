package com.example.train.bean

/**
 * 个人的缺勤记录
 * Created by chenyang
 * on 19-10-19d
 */
data class PersonalAbsentBean(
    val data: Data,
    val info: String,
    val status: Int
) {

    data class Data(
        val project: String,
        val reason: String,
        val time: String
    )

}
