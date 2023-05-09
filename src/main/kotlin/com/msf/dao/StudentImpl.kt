package com.msf.dao

import com.msf.mapping.Students
import com.msf.tables.*
import com.msf.dao.DatabaseFactory.dbQuery
import com.msf.tables.Student.uniqueIndex
import org.jetbrains.exposed.sql.ResultRow
import org.jetbrains.exposed.sql.insert
import org.jetbrains.exposed.sql.select
import org.jetbrains.exposed.sql.selectAll

class StudentImpl():StudentInterface {

    override  fun getAllStudents(row: ResultRow): Students = Students(stNo = row[Student.stNo], stName = row[Student.sName], sCourse = row[Student.sCourse], Class = row[Student.Class], bDate = row[Student.bDate])

    override suspend fun getAStudent(): Students  = dbQuery{
        TODO()
    }

    override suspend fun insertAStudent(students: Students): Unit = dbQuery{
        println("get datta$students.stName")
        Student.insert {
            it[this.stNo] = students.stNo
            it[this.sName] = students.stName
            it[this.bDate] = students.bDate
            it[this.Class]   = students.Class
            it[this.sCourse] = students.sCourse
        }
    }

    override suspend fun deleteAStudent(): Students? {
        TODO("Not yet implemented")
    }

    override suspend fun updateStudent(): Students? {
        TODO("Not yet implemented")
    }
    suspend fun getAllStudentDetails():List<Students> = dbQuery{

        val studentDetails = Student.selectAll().map {
            resultRow -> getAllStudents(resultRow)
        }
        return@dbQuery studentDetails
    }
}