package com.example.train

import android.app.Application
import android.content.Context
import androidx.multidex.MultiDex

/**
 * Created by chenyang
 * on 20-2-26
 */
class App: Application() {

    override fun onCreate() {
        super.onCreate()
        MultiDex.install(this)
        context = this
    }

    companion object{
        lateinit var context: Context
        private set
    }

}