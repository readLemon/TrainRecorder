package com.example.train.util

import android.annotation.SuppressLint
import android.content.Context
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.widget.Toast
import androidx.annotation.LayoutRes
import com.example.train.App

/**
 * Created by chenyang
 * on 20-3-14
 */
object ToastUtil {

    private var toast: Toast? = null
    private var customedToast: Toast ?= null

    @SuppressLint("ShowToast")
    fun showMsg(msg: String, duration: Int = Toast.LENGTH_SHORT) {
        if (toast == null) {
            toast = Toast.makeText(App.context, msg, duration)
        } else {
            toast?.setText(msg)
            toast?.duration = duration
        }
        toast?.show()
    }

    fun showCustomToast(
        @LayoutRes layoutId: Int,
        bingView: View.() -> Unit,
        context: Context,
        duration: Int = Toast.LENGTH_SHORT
    ) {
        val view = LayoutInflater.from(context).inflate(layoutId, null)
        view.bingView()

        if (customedToast == null) {
            customedToast = Toast(context)
            (customedToast as Toast).duration = duration
            (customedToast as Toast).setGravity(Gravity.CENTER, 0, 0)
        }
        (customedToast as Toast).view = view
        (customedToast as Toast).show()

    }


}