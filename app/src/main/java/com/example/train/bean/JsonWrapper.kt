package com.example.train.bean

import com.google.gson.Gson

/**
 * Created by chenyang
 * on 20-2-26
 */
class JsonWrapper<T> {

    var data: T ?= null
    var status = -1
    var info = ""

    override fun toString(): String {
        return Gson().toJson(this)
    }
}