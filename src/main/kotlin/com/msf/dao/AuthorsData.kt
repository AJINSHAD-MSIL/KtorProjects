
package com.msf.dao
import com.msf.tables.Books
import com.msf.dao.DatabaseFactory.dbQuery
import com.msf.mapping.AuthorDetails
import com.msf.mapping.Authors
import com.msf.mapping.Book
import com.msf.tables.Author
import com.msf.tables.BookInterface
import org.jetbrains.exposed.sql.*
import org.jetbrains.exposed.sql.SqlExpressionBuilder.eq

class AuthorsData: BookInterface {

    fun getAllEmployee(row: ResultRow) =
        row[Books.pub]?.let { Book( isbn = row[Books.isbn], author = row[Books.author], title = row[Books.title],pub = row[Books.pub]) }

    override suspend fun showSingleBook(isbn: Int): Book? =  dbQuery {
        val insertStatement = Books.insert {
            it[Books.isbn] = isbn

        }
        insertStatement.resultedValues?.singleOrNull()?.let(::getAllEmployee)
    }

    override suspend fun getAllBooks(): Book  = dbQuery{
        TODO()
    }

    override suspend fun addBook(isbn: Int, title: String, auther: String, pub:Int):Unit = dbQuery{
        println("isbn$isbn + title $title author $auther pub $pub")
        Books.insert {
            it[Books.isbn] = isbn
            it[Books.title] = title
            it[author] = auther
            it[Books.pub] = pub

        }
    }

    override suspend fun deleteBook(id: Int): Int= dbQuery{
        Author.deleteWhere{ Books.isbn eq id }
    }

    override suspend fun updateBook(isbn: Int, title:String,auther: String,pub:Int): Boolean = dbQuery{
        Books.update({ Books.isbn eq isbn }) {
            it[Books.title] = title
            it[Books.author] = auther
            it[Books.pub] = pub

        } > 0
    }
    suspend fun getAuthorDetails(isbn: Int): AuthorDetails? = dbQuery{
        val userWithAddress = (Books innerJoin Author)
            .select { Books.pub eq isbn }
            .map { row ->
                AuthorDetails(
                    isbn = row[Books.isbn],
                    title = row[Books.title],
                    author = row[Books.author],
                    pub = row[Books.pub],
                    country = row[Author.country]
                )
            }.singleOrNull()
        return@dbQuery userWithAddress
    }


    suspend fun getAuthors(id: Int) : Authors? = dbQuery {
      val response =   Author.select { Author.id eq id }
        .map { row ->
            Authors(
                id = row[Author.id],
                name = row[Author.name],
                country = row[Author.country],
            )}.singleOrNull()
            return@dbQuery response
    }

}