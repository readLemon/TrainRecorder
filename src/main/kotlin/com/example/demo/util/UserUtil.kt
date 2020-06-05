package com.example.demo.util

import com.example.demo.bean.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate
import org.springframework.stereotype.Service
import java.util.*
/**
 * created by chenyang
 * on 2020/4/18
 */
val defaultUser = User(
        name = "default",
        age = 0,
        isCaptain = false,
        psw = ""
)
@Service
class UserUtil {
    @Autowired
    lateinit var namedParameterJdbcTemplate: NamedParameterJdbcTemplate



    fun isUserExist(username: String, sql: String =
            "slect count(*) from user where name=:name"): Boolean {
        val params: MutableMap<String, String?> = HashMap()
        params["name"] = username
        val num = namedParameterJdbcTemplate.queryForObject(sql, params, Int::class.java)!!
        println(num)
        return num != 0
    }

    fun isUserExist(username: String): Boolean {
        val sql = "select count(*) from user where name = :username"
        val params: MutableMap<String, String?> = HashMap()
        params["username"] = username
        val num = namedParameterJdbcTemplate.queryForObject(sql, params, Int::class.java)!!
        println(num)
        return num == 0
    }
}
