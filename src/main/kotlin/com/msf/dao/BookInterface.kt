package com.msf.tables

import com.msf.mapping.Book


interface BookInterface {

    suspend fun showSingleBook(isbn:Int): Book?
    suspend fun getAllBooks(): Book?
    suspend fun addBook(isbn: Int, title:String,auther: String,pub:Int)
    suspend fun deleteBook(id: Int):Int
    suspend fun updateBook(isbn: Int, title:String,auther: String,pub:Int):Boolean

}