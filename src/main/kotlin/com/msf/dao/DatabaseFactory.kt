package com.msf.dao

import com.msf.tables.Books
import com.msf.tables.Author
import com.msf.tables.Course
import com.msf.tables.Student
import com.zaxxer.hikari.HikariConfig
import com.zaxxer.hikari.HikariDataSource
import io.ktor.server.config.*
import kotlinx.coroutines.Dispatchers
import org.jetbrains.exposed.sql.Database
import org.jetbrains.exposed.sql.SchemaUtils
import org.jetbrains.exposed.sql.transactions.experimental.newSuspendedTransaction
import org.jetbrains.exposed.sql.transactions.transaction

object DatabaseFactory {
    fun init(config: ApplicationConfig) {
        val database = Database.connect(createHikariDataSource(url = config.property("storage.jdbcURL").getString(), driver = config.property("storage.driverClassName").getString(),usernames = config.property("storage.username").getString(),passwords = config.property("storage.password").getString()))
        transaction(database) {
            SchemaUtils.createMissingTablesAndColumns(Author)
            SchemaUtils.createMissingTablesAndColumns(Books)
            SchemaUtils.createMissingTablesAndColumns(Course)
            SchemaUtils.createMissingTablesAndColumns(Student)
        }
    }
    private fun createHikariDataSource(url: String, driver: String, usernames:String, passwords:String) = HikariDataSource(HikariConfig().apply {
        driverClassName = driver
        jdbcUrl = url
        maximumPoolSize = 3
        isAutoCommit = false
        transactionIsolation = "TRANSACTION_REPEATABLE_READ"
        username = usernames
        password = passwords
        validate()
    })
    suspend fun <T> dbQuery(block: () ->T): T =
        newSuspendedTransaction(Dispatchers.IO) { block() }

}