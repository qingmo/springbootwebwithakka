package org.chaos.springbootwebwithakka.config

import akka.actor.Props
import akka.actor.typed.Extension
import org.springframework.context.ApplicationContext
import org.springframework.stereotype.Component

@Component("springAkkaExtension")
class SpringAkkaExtension : Extension {
    private var applicationContext: ApplicationContext? = null

    /**
     * 装填参数
     * @param applicationContext springcontext上下文
     */
    fun initialize(applicationContext: ApplicationContext?) {
        this.applicationContext = applicationContext
    }

    fun props(actorBeanName: String?): Props? {
        return Props.create(SpringActorProducer::class.java, applicationContext, actorBeanName)
    }

    fun props(actorBeanName: String?, vararg args: Any?): Props? {
        return Props.create(SpringActorProducer::class.java, applicationContext, actorBeanName, args)
    }
}