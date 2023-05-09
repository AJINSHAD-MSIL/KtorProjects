package com.msf.routes

import com.msf.dao.StudentImpl
import com.msf.mapping.Students
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.StudentRouting() {

    route("/student")
    {
        get("/allStudents")
        {
            val allStudents = StudentImpl().getAllStudentDetails()
            call.respondText(allStudents.toString())
        }
        post("/insertStudent")
        {
            val getStudent = call.receive<Students>()
            StudentImpl().insertAStudent(getStudent)
        }
    }

}