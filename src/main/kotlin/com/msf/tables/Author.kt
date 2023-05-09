package com.msf.tables
import org.jetbrains.exposed.dao.IntEntity
import org.jetbrains.exposed.dao.IntEntityClass
import org.jetbrains.exposed.dao.id.EntityID
import org.jetbrains.exposed.sql.Table

object Author: Table() {
    val id = integer("id").autoIncrement()
    val name = varchar("name", 128)
    val country = varchar("age",258)


    override val primaryKey = PrimaryKey(id)
}

