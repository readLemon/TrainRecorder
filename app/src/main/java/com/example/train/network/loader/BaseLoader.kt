package com.example.train.network.loader

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by chenyang
 * on 20-1-17
 */
abstract class BaseLoader {

    abstract val clazz: Class<out Any>

    protected fun <T> observe(observable: Observable<T>): Observable<T> {

        return observable
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}