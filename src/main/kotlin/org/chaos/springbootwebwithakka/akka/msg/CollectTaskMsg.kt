package org.chaos.springbootwebwithakka.akka.msg

import akka.actor.ActorRef

data class CollectTaskMsg(val target: String, val next: ActorRef)
