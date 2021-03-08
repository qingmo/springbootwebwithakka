package org.chaos.springbootwebwithakka.akka.actors

import akka.actor.ActorSystem
import akka.actor.Props
import akka.testkit.javadsl.TestKit
import org.chaos.springbootwebwithakka.akka.mocks.MockTaskExecServiceImpl
import org.chaos.springbootwebwithakka.akka.msg.CollectResultMsg
import org.chaos.springbootwebwithakka.akka.msg.CollectTaskMsg
import org.junit.jupiter.api.Assertions
import org.junit.jupiter.api.Test
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest
import java.time.Duration

@SpringBootTest
internal class TaskExecActorTest {

    @Autowired
    private val actorSystem: ActorSystem? = null

    @Test
    fun `test TaskExecActor receive with valid data`() {
        object: TestKit(actorSystem) {
            init {
                val service = MockTaskExecServiceImpl()
                val props = Props.create(TaskExecActor::class.java, service)
                val targetActor = actorSystem!!.actorOf(props)
                targetActor.tell(CollectTaskMsg("a", ref), ref)
                val msg = expectMsgClass(CollectResultMsg::class.java)
                Assertions.assertNotNull(msg)
                Assertions.assertEquals(service.MOCKED_EXEC_RESULT, (msg as CollectResultMsg).ret)
                Assertions.assertEquals("a", msg.param)
                Assertions.assertEquals(1, service.execTimes)
            }
        }

    }
}