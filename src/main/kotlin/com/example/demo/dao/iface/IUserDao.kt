package com.example.demo.dao.iface

import com.example.demo.bean.User

interface IUserDao {
    fun register(username: String, psw: String): String
    fun login(username: String, psw: String): User
}