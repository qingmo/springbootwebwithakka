package org.chaos.springbootwebwithakka.service

interface TaskExecService {

    fun exec(args: String): String
}