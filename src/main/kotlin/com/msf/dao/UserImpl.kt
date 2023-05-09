package com.msf.dao


import com.msf.dao.DatabaseFactory.dbQuery
import com.msf.tables.Author
import com.msf.mapping.Authors
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class UserImpl:UserInterface {

    private fun  getAllUsers(row: ResultRow) =
        Authors(id = row[Author.id], name = row[Author.name], country = row[Author.country])

    override suspend fun allUsers(): List<Authors> = dbQuery {
        Author.selectAll().map(::getAllUsers)
    }


    override suspend fun insertUser(id: Int, name:String, country:String): Unit =  dbQuery {
        Author.insert {
            it[Author.id] = id
            it[Author.name] = name
            it[Author.country] = country
        }
    }

    override suspend fun showSingleUser(id: Int): Authors? =  dbQuery {
        Author.select { Author.id eq id }
            .map(::getAllUsers)
            .singleOrNull()

    }

    override suspend fun deleteUser(id: Int): Int = dbQuery{
        Author.deleteWhere{ Author.id eq id }
    }

    override suspend fun updateUser(id: Int, name: String): Boolean = dbQuery{
        Author.update({ Author.id eq id }) {
            it[Author.id] = id
            it[Author.name] = name
        } > 0

    }
}