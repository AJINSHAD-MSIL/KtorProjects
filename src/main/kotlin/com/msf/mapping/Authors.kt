package com.msf.mapping
import kotlinx.serialization.Serializable

@Serializable
data class Authors(val id:Int, val name:String, val country:String)

@Serializable
data class Book(val isbn:Int, val title:String, val author:String, val pub:Int)

data class AuthorDetails(val isbn:Int, val title:String, val author:String, val pub:Int,val country:String)


@Serializable
data class Students(val stNo:Int,val stName:String,val sCourse:String,val Class:Int,val bDate:Int)


@Serializable
data class Course(val COURSE_NAME:String,val COURSE_NUMBER:Int,val CREDIT_HOURS:Int,val STUDENT_NAME:String)


data class StudentCourses(val stNo:Int,val STUDENT_NAME:String,val COURSE_NAME:String)

data class TotalStudents(val COURSE_NUMBER:Int,val COURSE_NAME:String,val STUDENT_NAME:String)
