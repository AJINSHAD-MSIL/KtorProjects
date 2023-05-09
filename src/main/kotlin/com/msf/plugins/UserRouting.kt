package com.msf.plugins

import com.msf.routes.StudentRouting
import io.ktor.server.application.*
import io.ktor.server.routing.*
import com.msf.routes.userRouting

fun Application.mainRouting(){
    routing {
        userRouting()
        book()
        StudentRouting()
    }}



