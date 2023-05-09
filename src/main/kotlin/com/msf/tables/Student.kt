package com.msf.tables

import com.msf.tables.Course.COURSE_NAME
import org.jetbrains.exposed.sql.Table


object Student: Table() {

    val stNo = integer("STNO")
    val sName = varchar("SNAME",1024).uniqueIndex()
    val sCourse = varchar("StudentCourses",1024)
    val Class = integer("CLASS")
    val bDate = integer("BDATE")

    override val primaryKey = PrimaryKey(stNo)
}