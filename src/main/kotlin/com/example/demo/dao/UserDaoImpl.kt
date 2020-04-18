package com.example.demo.dao


import com.example.demo.bean.ApiConfig
import com.example.demo.bean.User
import com.example.demo.dao.iface.IUserDao
import com.example.demo.util.defaultUser
import org.springframework.jdbc.core.RowMapper
import org.springframework.stereotype.Repository
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.SQLException

@Repository("userDao")
class UserDaoImpl : BaseDao(), IUserDao {

    override fun register(username: String, psw: String): String {
        val insertSql = "INSERT INTO user (name, psw) VALUES(:name, :psw)"
        //        String autoAddSql = "ALTER TABLE user AUTO_INCREMENT =1";
//        Statement statement;
        return try {
           val param = HashMap<String, Any>().apply {
               put("name", username)
               put("psw", psw)
           }
            val result = namedParameterJdbcTemplate.update(insertSql, param)
            ApiConfig.ResponseInfo.REQUEST_SUCCESSFULL
        } catch (e: SQLException) {
            e.printStackTrace()
            ApiConfig.ResponseInfo.REQUEST_FAILED
        }
    }

    override fun login(username: String, psw: String): User {
        val sql = "SELECT * FROM user WHERE name= :username and psw=:psw"
        val params = hashMapOf<String, Any>("username" to username, "psw" to psw)

        var userResult = defaultUser
         try {
             userResult = namedParameterJdbcTemplate.queryForObject(sql, params, User::class.java)!!
            println(userResult.toString())
        } catch (e: SQLException) {
            e.printStackTrace()
        }
        return userResult
    }

}