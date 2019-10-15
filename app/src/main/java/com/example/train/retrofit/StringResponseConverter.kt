package com.example.train.retrofit

import okhttp3.ResponseBody
import retrofit2.Converter

/**
 * Created by chenyang
 * on 19-10-10
 */
class StringResponseConverter : Converter<ResponseBody, String> {
    override fun convert(value: ResponseBody): String {
        return value.string()
    }
}