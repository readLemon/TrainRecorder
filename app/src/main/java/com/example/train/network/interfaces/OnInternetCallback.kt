package com.example.train.network.interfaces

import retrofit2.Response

/**
 * Created by chenyang
 * on 19-10-14
 */
interface OnInternetCallback<T> {

    fun onFailed()

    fun onSuccessful(response: Response<T>)
}