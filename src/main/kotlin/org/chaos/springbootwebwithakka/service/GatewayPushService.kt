package org.chaos.springbootwebwithakka.service

interface GatewayPushService {

    fun push(msg: String, param: String)
}