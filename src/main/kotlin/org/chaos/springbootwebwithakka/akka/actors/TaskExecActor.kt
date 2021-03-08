package org.chaos.springbootwebwithakka.akka.actors

import akka.actor.UntypedAbstractActor
import org.chaos.springbootwebwithakka.akka.msg.CollectResultMsg
import org.chaos.springbootwebwithakka.akka.msg.CollectTaskMsg
import org.chaos.springbootwebwithakka.service.TaskExecService
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.beans.factory.config.ConfigurableBeanFactory
import org.springframework.context.annotation.Scope
import org.springframework.stereotype.Component

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
class TaskExecActor(val taskExecService: TaskExecService) : UntypedAbstractActor() {
    private val log: Logger = LoggerFactory.getLogger(this.javaClass)

    override fun onReceive(message: Any?) {
        if (message is CollectTaskMsg) {
            val execRet = taskExecService!!.exec(message.target)
            log.info("execRet:{}", execRet)
            message.next.tell(CollectResultMsg(execRet, message.target), self)
        } else {
            unhandled(message)
        }
    }
}