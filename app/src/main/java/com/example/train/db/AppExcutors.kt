package com.example.train.db

import java.util.concurrent.Executor
import java.util.concurrent.Executors

/**
 * Created by chenyang
 * on 20-3-20
 * 先暂时放这儿等那天来搞。。。准备给model层加上异步
 */
class AppExcutors {
    private val IOExcutor: Executor

    init {
        IOExcutor = Executors.newSingleThreadExecutor()
    }

     fun getExecutor(): Executor {
        return IOExcutor
    }
}