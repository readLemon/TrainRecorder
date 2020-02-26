package com.example.train.network

import com.example.train.bean.JsonWrapper
import io.reactivex.functions.Function

/**
 * Created by chenyang
 * on 20-2-26
 */
class JsonWrapperFunc<T>: Function<JsonWrapper<T>, T> {
    override fun apply(t: JsonWrapper<T>): T {
        if (t.status != 200) {
            throw Fault(t.status, t.info)
        }

        if (t.data == null) {
            throw java.lang.RuntimeException("the response data is null")
        }else{
            return  t.data as T
        }

    }


}