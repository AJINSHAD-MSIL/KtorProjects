package com.msf

import com.msf.dao.DatabaseFactory
import io.ktor.server.application.*
import io.ktor.server.netty.*
import com.msf.plugins.*
import io.ktor.serialization.kotlinx.json.*
import io.ktor.server.plugins.contentnegotiation.*

fun main(args: Array<String>): Unit = EngineMain.main(args)

fun Application.module() {
    DatabaseFactory.init(environment.config)
    mainRouting()
    install(ContentNegotiation)
    {
        json()
    }

}
