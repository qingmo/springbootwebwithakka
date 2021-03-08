package org.chaos.springbootwebwithakka.service

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service

@Service
class GatewayPushServiceImpl : GatewayPushService {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)
    override fun push(msg: String, param: String) {
        log.info("push " + msg + param)
    }
}