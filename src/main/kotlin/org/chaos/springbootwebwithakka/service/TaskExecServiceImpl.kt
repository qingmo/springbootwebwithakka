package org.chaos.springbootwebwithakka.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class TaskExecServiceImpl : TaskExecService {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun exec(args: String): String {
        log.info("handled exec with argument:{}", args)
        return "execresult"
    }
}