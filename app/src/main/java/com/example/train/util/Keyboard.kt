package com.example.train.util

import android.app.Activity
import android.content.Context
import android.view.inputmethod.InputMethodManager

/**
 * Created by chenyang
 * on 19-10-22
 */


/**
 * 隐藏键盘的方法
 *
 * @param context
 */
public fun hideKeyboard(context: Context) {
    val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    // 隐藏软键盘
    imm.hideSoftInputFromWindow(
        (context as Activity).getWindow().getDecorView().getWindowToken(),
        0
    );
}
