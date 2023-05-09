package com.msf.plugins

import com.msf.dao.AuthorsData
import com.msf.mapping.Book
import io.ktor.server.application.*
import io.ktor.server.request.*
import io.ktor.server.response.*
import io.ktor.server.routing.*

fun Route.book() {
    route("/book")
    {
        get("/showSingleBook/{id}")
        {
                val isbnNumber = call.parameters["id"]?.toInt()
            val book = isbnNumber?.let { it1 -> AuthorsData().showSingleBook(it1) }
            call.respondText(book.toString())
        }
        post("/insertBook")
        {
            try {
                val insertBook = call.receive<Book>()
                AuthorsData().addBook(insertBook.isbn,insertBook.title,insertBook.author,insertBook.pub)
            }catch (e:Exception){
                call.respond(e.message.toString())
            }

        }
        put("/updateBook")
        {
            val updateBook = call.receive<Book>()
            AuthorsData().updateBook(updateBook.isbn,updateBook.title,updateBook.author,updateBook.pub)

        }
        get("/showSingleBook/{id}")
        {
            call.respond("working")
            val singleBook = call.parameters["id"]?.let { it1 -> AuthorsData().showSingleBook(it1.toInt()) }
            call.respondText(singleBook.toString())
        }
        get("/getSingleBookRecord/{id}")
        {
            val list = mutableMapOf<Any,Any>()
            val id = call.parameters["id"]?.toInt()
            if(id != null) {
                val response = AuthorsData().getAuthorDetails(id)
                response?.isbn?.let { it1 -> list.put(it1,it1) }
                response?.pub?.let { it1 -> list.put(it1,it1) }
                response?.author?.let { it1 -> list.put(it1,it1) }
                response?.country?.let { it1 -> list.put(it1,it1) }
                response?.title?.let { it1 -> list.put(it1,it1) }
                call.respondText(list.toString())
            }
        }
        get("/getAuthor/{id}")
        {
            val id = call.parameters["id"]?.toInt()
            if (id != null) {
                val response = AuthorsData().getAuthors(id)
                call.respondText(response.toString())
            }
        }
    }
}