package com.example.demo.controller

import com.example.demo.service.TrainServiceImpl
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestParam
import org.springframework.web.bind.annotation.RestController

/**
 * created by chenyang
 * on 2020/2/22
 */
@RestController
class TrainController {
    @Autowired
    private lateinit var trainService: TrainServiceImpl

    @PostMapping(value = ["/addAbsent"])
    fun addAbsent(@RequestParam username: String,
                  @RequestParam time: Long,
                  @RequestParam project: String): String {
        return trainService.addAbsent(username, time, project)
    }

    @PostMapping(value = ["/addLeave"])
    fun addLeave(@RequestParam username: String,
                 @RequestParam time: Long,
                 @RequestParam project: String,
                 @RequestParam reason: String): String {
        return trainService.addLeave(username, time, project, reason)
    }

    @PostMapping(value = ["/getAbsents"])
    fun getAbsents(@RequestParam username: String): String {
        return trainService.getPersonalAbsents(username)
    }

    @PostMapping(value = ["/getLeaves"])
    fun addAbsent(@RequestParam username: String): String {
        return trainService.getPersonalLeaves(username)
    }
}