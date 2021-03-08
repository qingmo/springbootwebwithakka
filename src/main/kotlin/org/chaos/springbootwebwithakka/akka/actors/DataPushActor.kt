package org.chaos.springbootwebwithakka.akka.actors

import akka.actor.UntypedAbstractActor
import org.chaos.springbootwebwithakka.akka.msg.CollectResultMsg
import org.chaos.springbootwebwithakka.service.GatewayPushService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class DataPushActor(val gatewayPushService: GatewayPushService) : UntypedAbstractActor() {

    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun onReceive(message: Any?) {
        if (message is CollectResultMsg) {
            log.info("actor pushed Msg:{}", message.toString())
            gatewayPushService.push(message.ret, message.param)
        } else {
            unhandled(message)
        }
    }
}