package com.example.demo.controller

import com.example.demo.service.TeamServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * created by chenyang
 * on 2020/2/26
 */
@RestController
class TeamConroller {
    @Autowired
    private lateinit var teamService: TeamServiceImpl

    @PostMapping(value = ["/addTeamData"], produces = ["application/json;charset=UTF-8"])
    fun addTeamData(
            @RequestParam teamName: String,
            @RequestParam project: String,
            @RequestParam time: Long
    ): String {
        return teamService.addTeamData(teamName, project, time)
    }

    @GetMapping(value = ["/getTeamData"])
    fun getTeamData(@RequestParam teamName: String): String {
        return teamService.getTeamData(teamName)
    }
}