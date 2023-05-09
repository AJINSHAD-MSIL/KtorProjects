package com.msf.dao

import com.msf.mapping.Course

interface CourseInterface {
    suspend fun getAllCourses():List<Course>
    suspend fun getACourse(): Course
    suspend fun insertACourse(): Course?
    suspend fun deleteACourse(): Course?
    suspend fun updateCourse(): Course?
}