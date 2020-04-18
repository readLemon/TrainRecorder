package com.example.demo.bean

/**
 * created by chenyang
 * on 2020/2/26
 */
data class TeamResult (
    @Transient
    var teamName: String,
    var trainData: Any,
    var totalTrainCount: Int
){

}