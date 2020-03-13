package com.example.train.util.extention

import android.content.Context
import android.content.SharedPreferences
import com.example.train.config.DEFAULT_SHARED_PREFERENCE_XML_NAME

/**
 * Created by chenyang
 * on 20-3-12
 */

val Context.mySharedPreferences
    get() = sharedPreferences(DEFAULT_SHARED_PREFERENCE_XML_NAME)

fun Context.sharedPreferences(name: String) = getSharedPreferences(name, Context.MODE_PRIVATE)

fun SharedPreferences.editor(mEditor: SharedPreferences.Editor.() -> Unit) =
    edit().apply(mEditor).apply()

