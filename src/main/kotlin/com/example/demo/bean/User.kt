package com.example.demo.bean

import javax.persistence.Column

/**
 * created by chenyang
 * on 2020/4/17
 */

data class User(
        val name: String,
        val age: Int,
        @Column(name = "is_captain")
        val isCaptain: Boolean,

        @Transient val psw: String
){
    var id: Int ?= null

    override fun toString(): String {
        return """name -> $name
            |age -> $age
            |isCaptain $isCaptain
            |psw -> $psw
        """.trimMargin()
    }
}