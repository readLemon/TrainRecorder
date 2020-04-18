package com.example.demo.bean

class ApiConfig {
    object ResponseStatus {
        //请求成功
        const val REQUEST_SUCCESSFUL = 200

        //请求失败
        const val REQUEST_FAILED = 201

        //用户不存在
        const val INVALIB_USER = 114

        //加入缺席次数失败
        const val ADD_ABSENT_FAILED = 911

        //加入请假次数失败
        const val ADD_LEAVE_FAILED = 110

        //获取个人缺席数据失败
        const val GET_ABSENTS_FAILED = 120

        //获取个人请假数据失败
        const val GET_LEAVES_FAILED = 119

        //获取团队数据失败
        const val GET_TEAM_DATA_FAILED = 404

        //增加团队数据失败
        const val ADD_TEAM_DATA_FAILED = 101

        //加入迟到数据失败
        const val ADD_LATE_FAILED = 435

        //获取迟到数据失败
        const val GET_LATES_FAILED = 436
    }

    object ResponseInfo {
        const val REQUEST_SUCCESSFULL = "successfull"
        const val REQUEST_FAILED = "faile"
    }

    object UserInfo {
        const val INVALIB_USER = "This user does not exist!"
        const val REGISER_SUCCESSFULL = "rigister successfull"
        const val REGISER_FAILED = "rigister failed"
        const val LOGIN_SUCCESSFULL = "Login successfull"
        const val LOGIN_FAILED = "Login failed"
    }

    object TrainInfo {
        const val ADD_ABSENT_SUCCESS = "add absent successful"
        const val ADD_ABSENT_FAILED = "add absent failed"
        const val ADD_LEAVE_SUCCESS = "add leave successful"
        const val ADD_LEAVE_FAILED = "add leave failed"
        const val GET_ABSENTS_SUCCESSFUL = "get absents successful"
        const val GET_ABSENTS_FAILED = "get absents failed"
        const val GET_LEAVES_SUCCESSFULL = "get leaves successful"
        const val GET_LEAVES_FAILED = "get leaves failed"
    }

    object TeamInfo {
        const val GET_DATA_SUCCESSFUL = "GET_DATA_SUCCESSFUL"
        const val GET_DATA_FAILED = "GET_DATA_Failed"
        const val ADD_DATA_SUCCESSFUL = "ADD_DATA_SUCCESSFUL"
        const val ADD_DATA_FAILED = "ADD_DATA_Failed"
    }

    object LateInfo {
        const val GET_LATES_SUCCESSFUL = "GET_LATES_SUCCESSFUL"
        const val GET_LATES_FAILED = "GET_LATES_FAILED"
        const val ADD_LATES_FAILED = "ADD_LATES_FAILED"
        const val ADD_LATES_SUCCESSFUL = "ADD_LATES_SUCCESSFUL"
    }
}