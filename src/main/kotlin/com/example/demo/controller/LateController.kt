package com.example.demo.controller

import com.example.demo.service.LateServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.RestController

/**
 * created by chenyang
 * on 2020/3/22
 */
@RestController
class LateController {
    @Autowired
    private lateinit var lateService: LateServiceImpl

    @PostMapping(value = ["/addLate"])
    fun addLate(
            @RequestParam("username") username: String,
            @RequestParam("time") time: Long,
            @RequestParam("duration") duration: Int,
            @RequestParam("project") project: String,
            @RequestParam("team") team: String
    ): String {
        return lateService.addLate(username, time, duration, project, team)
    }

    @PostMapping(value = ["/getLates"])
    fun getLates(
            @RequestParam("username") username: String,
            @RequestParam("teamname") teamname: String): String {
        return lateService.getLates(username, teamname)
    }
}