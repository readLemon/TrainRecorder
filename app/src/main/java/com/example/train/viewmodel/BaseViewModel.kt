package com.example.train.viewmodel

import androidx.lifecycle.ViewModel
import io.reactivex.disposables.CompositeDisposable
import kotlin.properties.Delegates

/**
 * Created by chenyang
 * on 20-3-10
 */
open class BaseViewModel: ViewModel() {

    val mCompositeDisposable by Delegates.notNull<CompositeDisposable>()


    fun clearDisposable() {
        mCompositeDisposable.clear()
    }

}