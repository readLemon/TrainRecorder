package com.mredrock.cyxbs.common.utils

import android.util.Log

/**
 * log工具
 * Created by Jay on 2017/10/10.
 */

object LogUtil {


    fun v(tag: String = "tag", msg: String, throwable: Throwable? = null) {
        Log.v(tag, msg, throwable)
    }

    fun d(tag: String = "tag", msg: String, throwable: Throwable? = null) {
        Log.d(tag, msg, throwable)
    }

    fun i(tag: String = "tag", msg: String, throwable: Throwable? = null) {
        Log.i(tag, msg, throwable)
    }

    fun w(tag: String = "tag", msg: String, throwable: Throwable? = null) {
        Log.w(tag, msg, throwable)
    }

    fun e(tag: String = "tag", msg: String, throwable: Throwable? = null) {
        Log.e(tag, msg, throwable)
    }
}