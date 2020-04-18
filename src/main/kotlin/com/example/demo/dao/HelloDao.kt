package com.example.demo.dao

import com.example.demo.bean.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Repository

/**
 * created by chenyang
 * on 2020/4/17
 */
@Repository
class HelloDao {

    @Autowired(required = true)
    private lateinit var namedParameterJdbcTemplate: NamedParameterJdbcTemplate

    fun addUser(user: User) {
        val sql = "insert into user (name, psw, age, is_captain ) values (:name, :psw, :age, :isCaptain )"

        val param = HashMap<String, Any>()
        param.apply {
            put("name", user.name)
            put("psw", user.psw)
            put("age", user.age)
            put("isCaptain", user.isCaptain)
        }
        val result = namedParameterJdbcTemplate.update(sql, param)
    }
}