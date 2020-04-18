package com.example.demo.dao

import org.springframework.beans.factory.annotation.Autowired
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate

/**
 * created by chenyang
 * on 2020/4/18
 */

abstract class BaseDao {
    @Autowired(required = true)
    lateinit var namedParameterJdbcTemplate: NamedParameterJdbcTemplate



}