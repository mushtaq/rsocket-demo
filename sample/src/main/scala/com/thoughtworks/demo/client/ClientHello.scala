package com.thoughtworks.demo.client

import io.rsocket.akka.client.ClientWiring
import io.rsocket.util.DefaultPayload

object ClientHello {
  def main(args: Array[String]): Unit = {
    val wiring = new ClientWiring()
    import wiring._
    wiring.socket().foreach { socket =>
      socket.requestResponse(DefaultPayload.create("mushtaq")).foreach(x => println(x.getDataUtf8))
    }
  }
}
