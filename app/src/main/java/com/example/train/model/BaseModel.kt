package com.example.train.model

import com.example.train.db.AppExcutors
import java.util.concurrent.Executor

/**
 * Created by chenyang
 * on 20-3-20
 */
open class BaseModel {

    val mIOExcutor: Executor

    init {
        mIOExcutor = AppExcutors().getExecutor()
    }
}