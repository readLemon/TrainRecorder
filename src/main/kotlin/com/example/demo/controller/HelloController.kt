package com.example.demo.controller

import com.example.demo.bean.User
import com.example.demo.service.HelloService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.*
import java.sql.ResultSet
import org.springframework.jdbc.core.RowMapper as RowMapper

/**
 * created by chenyang
 * on 2020/4/17
 */
@RestController
class HelloController {

    @Autowired
    private lateinit var helloService: HelloService

    @PostMapping(value = ["/hello"])
//    fun test(@RequestParam hehelo: String): String {
//    fun test(@RequestBody per: Map<String, String>): String { //raw
    fun test(@RequestParam per: Map<String, String>): String { //x-www-form-urlencoded

        for ( temp in per.entries)
        println("v -> ${temp.key}, v -> ${temp.value}")

//        println(hehelo)

        return "hello kotlin web"
    }

    inner class Mapper: RowMapper<User> {
        override fun mapRow(p0: ResultSet, p1: Int): User {
            val user = User(
                    name = p0.getString("name"),
                    age = p0.getInt("age"),
                    psw = p0.getString("psw"),
                    isCaptain = p0.getBoolean("is_captain")
            )
            user.id = p0.getInt("id")
            return user;
        }

    }






}