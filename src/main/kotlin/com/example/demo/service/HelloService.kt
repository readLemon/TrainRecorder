package com.example.demo.service

import com.example.demo.dao.HelloDao
import com.example.demo.bean.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service

/**
 * created by chenyang
 * on 2020/4/17
 */

@Service
class HelloService {

    @Autowired
    private lateinit var helloDao: HelloDao

    fun addUser(name: String, age: Int, psw: String, isCaptain: Boolean) {
        val user = User(name, age, isCaptain, psw)
        helloDao.addUser(user)
    }

}