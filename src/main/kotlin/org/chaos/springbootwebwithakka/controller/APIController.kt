package org.chaos.springbootwebwithakka.controller

import org.chaos.springbootwebwithakka.akka.ActorSubmitService
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RestController

@RestController
class APIController {

    @Autowired
    private val actorSubmitService: ActorSubmitService? = null

    @PostMapping("/greet")
    fun greetActor(@RequestBody msg: String): String {
        actorSubmitService!!.submitTask(msg)
        return "success"
    }
}