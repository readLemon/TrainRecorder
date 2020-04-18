package com.example.demo.util

import com.example.demo.bean.User
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

/**
 * created by chenyang
 * on 2020/4/18
 */
object UserUtil {
    @Autowired
    lateinit var namedParameterJdbcTemplate: NamedParameterJdbcTemplate
}

val defaultUser = User(
        name = "default",
        age = 0,
        isCaptain = false,
        psw = ""
)

public fun isUserExist(username: String, sql: String =
        "slect count(*) from user where name=:name"): Boolean {
    val params = hashMapOf<String, Any>("name" to username)

    val result = UserUtil.namedParameterJdbcTemplate.queryForObject(sql, params, Int::class.java)

    return result == 1
}