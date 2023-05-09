package com.msf.tables

import com.msf.tables.Student.sName
import org.jetbrains.exposed.sql.Table


object Course: Table() {

    val COURSE_NAME = varchar("COURSE_NAME",1024).uniqueIndex()
    val COURSE_NUMBER = varchar("COURSE_NUMBER",1024)
    val CREDIT_HOURS = integer("CREDIT_HOURS")
    val STUDENT_NAME = reference("STUDENT_NAME",sName)
}