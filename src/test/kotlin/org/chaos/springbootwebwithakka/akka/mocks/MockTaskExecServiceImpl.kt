package org.chaos.springbootwebwithakka.akka.mocks

import org.chaos.springbootwebwithakka.service.TaskExecService

class MockTaskExecServiceImpl: TaskExecService {
    var execTimes: Int = 0
    var execParam: String = ""
    val MOCKED_EXEC_RESULT = "mocked"
    override fun exec(args: String): String {
        execTimes++
        execParam = args
        return MOCKED_EXEC_RESULT
    }
}