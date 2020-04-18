package com.example.demo.controller

import com.example.demo.bean.ApiConfig
import com.example.demo.bean.Result
import com.example.demo.bean.User
import com.example.demo.service.UserServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

@RestController
class UserController {
    @Autowired
    private lateinit var userServiceImpl: UserServiceImpl

    @GetMapping(value = ["/login"])
    fun login(@RequestParam("code") code: String): String {
        println("login 被点击了code:$code")
        return userServiceImpl.login("cy", "999", code)
    }

    @GetMapping(value = ["/register"])
    fun register(
            @RequestParam("username") username: String,
            @RequestParam("password") password: String): String {
        println("register has be clicked")
        return userServiceImpl.register(username, password)
    }

    @GetMapping(value = ["/testJson"])
    fun register(): String {
        val use = User(
                name = "5555",
                psw = "55555",
                age = 2222,
                isCaptain = false
        )

        println("register has be clicked")
        val result = Result()
        result.status = ApiConfig.ResponseStatus.REQUEST_SUCCESSFUL
        result.info = ApiConfig.ResponseInfo.REQUEST_SUCCESSFULL
        result.data = use
        //        result.lisy.add(use);
        return Result.toJson(result)
    }
}