package com.example.train.retrofit.loader

import com.example.train.retrofit.RetrofitServiceManager
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers

/**
 * Created by chenyang
 * on 20-1-17
 */
abstract class BaseLoader<T> {


    abstract val clazz: Class<T>
    val service: T

    init {
        service = RetrofitServiceManager.getInstance().creat(clazz)
    }

    protected fun <T> observe(observable: Observable<T>): Observable<T> {

        return observable
            .subscribeOn(Schedulers.io())
            .unsubscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
    }


}