package org.chaos.springbootwebwithakka.akka

import akka.actor.ActorRef
import akka.actor.ActorSystem
import org.chaos.springbootwebwithakka.akka.msg.CollectTaskMsg
import org.chaos.springbootwebwithakka.config.SpringAkkaExtension
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.stereotype.Service
import javax.annotation.PostConstruct


@Service
class ActorSubmitService {

    @Autowired
    private val actorSystem: ActorSystem? = null

    @Autowired
    private val springAkkaExtension: SpringAkkaExtension? = null

    private var taskExecActor: ActorRef? = null
    private var dataPushActor: ActorRef? = null

    @PostConstruct
    fun setUp() {
        taskExecActor = actorSystem!!.actorOf(springAkkaExtension!!.props("taskExecActor"), "taskExecActor")
        dataPushActor = actorSystem!!.actorOf(springAkkaExtension!!.props("dataPushActor"), "dataPushActor")
    }

    fun submitTask(taskInfo: String) {
        val batchTasks = taskInfo.splitToSequence(",").toList()
        batchTasks.forEach{
            taskExecActor!!.tell(CollectTaskMsg(it, dataPushActor!!), null)
        }
    }
}