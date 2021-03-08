package org.chaos.springbootwebwithakka.config

import akka.actor.ActorSystem
import com.typesafe.config.Config
import com.typesafe.config.ConfigFactory
import org.chaos.springbootwebwithakka.utils.ACTOR_SYSTEM_NAME
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.context.ApplicationContext
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration


@Configuration
class AkkaConfiguration {
    @Autowired
    private val applicationContext: ApplicationContext? = null

    @Autowired
    private val springAkkaExtension: SpringAkkaExtension? = null

    @Bean(destroyMethod = "terminate")
    fun actorSystem(): ActorSystem? {
        val actorSystem: ActorSystem = ActorSystem.create(ACTOR_SYSTEM_NAME, akkaConfig())
        springAkkaExtension!!.initialize(applicationContext)
        return actorSystem
    }

    @Bean
    fun akkaConfig(): Config? {
        return ConfigFactory.load()
    }
}