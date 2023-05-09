package com.msf.dao

import com.msf.mapping.Students
import org.jetbrains.exposed.sql.ResultRow

interface StudentInterface {

    fun getAllStudents(row:ResultRow):Students
    suspend fun getAStudent():Students
    suspend fun insertAStudent(students: Students)
    suspend fun deleteAStudent():Students?
    suspend fun updateStudent():Students?
}