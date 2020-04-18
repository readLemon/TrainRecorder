package com.example.demo.service.iface

interface IUserService {
    fun register(username: String, psw: String): String
    fun login(username: String, psw: String, code: String): String
}