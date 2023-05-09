package com.msf.routes

import com.msf.dao.UserImpl
import com.msf.mapping.Authors
import com.msf.tables.Author
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.userRouting() {
    route("/users")
    {
        get("/allUsers")
        {
            val response = UserImpl().allUsers()
            call.respond(response)
        }
        post("/insertUser")
        {
            val users = call.receive<Authors>()
            UserImpl().insertUser(users.id,users.name, users.country)
        }
        get( "/showSingleUser/{id}")
        {
            val users = call.parameters["id"]?.toInt()
            val singleUser = users?.let { it1 -> UserImpl().showSingleUser(it1) }
            call.respond(singleUser.toString())
        }
        put("/UpdateUser/{id}")
        {
            val users = call.parameters["id"]?.toInt()
            val name = call.receive<Authors>()
            if (users != null) {
                UserImpl().updateUser(users,name.name)
            }
        }
        delete("/DeleteUser/{id}")
        {
            val user = call.parameters["id"]?.let { it1 -> UserImpl().deleteUser(it1.toInt())}
            call.respond("deleted user is $user")
        }
        post ("/userdata")
        {
            val parameters = call.receive<Author>()
            call.respond(parameters.name)
        }

    }
}