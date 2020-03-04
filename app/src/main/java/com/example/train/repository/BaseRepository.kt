package com.example.train.repository

import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by chenyang
 * on 20-3-5
 */
open class BaseRepository {

    protected fun <T> observe(observable: Observable<T>): Observable<T> {

        return observable
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }
}