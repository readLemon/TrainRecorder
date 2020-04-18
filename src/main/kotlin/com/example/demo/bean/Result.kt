package com.example.demo.bean

import com.google.gson.Gson
import com.google.gson.JsonNull

class Result {
    var status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
    var info = ApiConfig.ResponseInfo.REQUEST_SUCCESSFULL
    var data: Any? = null
    var list: List<Any>? = null

    fun toJson(): String {
        return gson.toJson(this)
    }

    companion object {
        var gson = Gson() //GsonBuilder()

        //            .excludeFieldsWithModifiers(Modifier.PROTECTED)
        //            .serializeNulls()
        //            .create();
        fun toJson(o: Any?): String {
            return if (o == null) {
                gson.toJson(JsonNull.INSTANCE)
            } else gson.toJson(o)
        }
    }
}