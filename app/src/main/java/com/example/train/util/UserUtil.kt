package com.example.train.util

import com.example.train.bean.UserBean

/**
 * Created by chenyang
 * on 20-3-4
 */
object UserUtil {

    private var currentUser: UserBean ?= null
    var currentUsername: String = "wronguser"

    fun replaceUser(user: UserBean) {
        currentUser = user
        currentUsername = user.name
    }

    fun getCurrentUser(): UserBean? {
        return currentUser
    }

}