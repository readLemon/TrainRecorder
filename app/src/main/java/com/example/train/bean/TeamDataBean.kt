package com.example.train.bean

/**
 * Created by chenyang
 * on 19-10-23
 */

data class TeamDataBean(
    val teamName: String,
    val totalTrainCount: Int,
    val trainData: List<TrainData>
){

    data class TrainData(
        val trainPro: String,
        val trainTime: Long
    )

}
