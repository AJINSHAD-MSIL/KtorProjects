package com.msf.dao

import com.msf.mapping.Authors


interface UserInterface {
    suspend fun allUsers():List<Authors>
    suspend fun insertUser(id: Int,name:String,country:String)
    suspend fun showSingleUser(id: Int):Authors?
    suspend fun deleteUser(id: Int):Int
    suspend fun updateUser(id: Int,name: String):Boolean
}