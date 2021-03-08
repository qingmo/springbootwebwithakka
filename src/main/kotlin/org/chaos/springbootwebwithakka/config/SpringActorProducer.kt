package org.chaos.springbootwebwithakka.config

import akka.actor.Actor
import akka.actor.IndirectActorProducer
import org.springframework.context.ApplicationContext

class SpringActorProducer : IndirectActorProducer {
    private val applicationContext: ApplicationContext
    private val actorBeanName: String
    private val args: Array<Any>?

    constructor(applicationContext: ApplicationContext, actorBeanName: String) {
        this.applicationContext = applicationContext
        this.actorBeanName = actorBeanName
        args = null
    }

    constructor(applicationContext: ApplicationContext, actorBeanName: String, args: Array<Any>?) {
        this.applicationContext = applicationContext
        this.actorBeanName = actorBeanName
        this.args = args
    }

    /**
     * 所有actor的创建都会调用此方法，
     * 此方法由Props.create会调用SpringActorProducer构造方法
     * 并靠构造器中的传入参数来获取对应的Actor bean，达到actor交由spring托管的效果
     * @return
     */
    override fun produce(): Actor {
        return if (args == null) {
            applicationContext.getBean(actorBeanName) as Actor
        } else {
            applicationContext.getBean(actorBeanName, *args) as Actor
        }
    }

    override fun actorClass(): Class<out Actor> {
        return applicationContext.getType(actorBeanName) as Class<out Actor>
    }
}
