package com.msf.tables

import com.msf.tables.Author.id
import org.jetbrains.exposed.sql.Table

object Books : Table()
{
    val isbn = integer("ISBN").autoIncrement()
    val title = varchar("Title", 256)
    val author = varchar("Author",258).default("")
    val pub = integer("Pub").references(id)

    override val primaryKey = PrimaryKey(isbn)

}