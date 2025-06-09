package com.example.bttuan_4.data.repository

import com.example.bttuan_4.data.LibraryDatabase
import com.example.bttuan_4.data.pojo.Book
import com.example.bttuan_4.data.pojo.Borrow
import com.example.bttuan_4.data.pojo.User
import com.example.bttuan_4.data.pojo.UserWithBooks

class LibraryRepository(private val db: LibraryDatabase) {

    suspend fun insertSampleData() {
        // User
        db.userDao().insertUsers(User(userName = "Nguyen Van A"))
        db.userDao().insertUsers(User(userName = "Tran Thi B"))
        db.userDao().insertUsers(User(userName = "Le Van C"))

        // Book
        db.bookDao().insertBooks(Book(name = "Lập trình Kotlin"))
        db.bookDao().insertBooks(Book(name = "Android căn bản"))
        db.bookDao().insertBooks(Book(name = "Thiết kế UI/UX"))
        db.bookDao().insertBooks(Book(name = "Cấu trúc dữ liệu"))
        db.bookDao().insertBooks(Book(name = "Toán rời rạc"))

        // Borrow
        val users = listOf(
            db.userDao().getUserByName("Nguyen Van A"),
            db.userDao().getUserByName("Tran Thi B"),
            db.userDao().getUserByName("Le Van C")
        )

        val books = listOf(
            db.bookDao().getBookByName("Lập trình Kotlin"),
            db.bookDao().getBookByName("Android căn bản"),
            db.bookDao().getBookByName("Thiết kế UI/UX"),
            db.bookDao().getBookByName("Cấu trúc dữ liệu"),
            db.bookDao().getBookByName("Toán rời rạc")
        )

        val borrows = mutableListOf<Borrow>()

        users[0]?.let { user ->
            books[0]?.let { borrows.add(Borrow(userId = user.userId, bookId = it.bookId)) }
            books[1]?.let { borrows.add(Borrow(userId = user.userId, bookId = it.bookId)) }
        }

        users[1]?.let { user ->
            books[2]?.let { borrows.add(Borrow(userId = user.userId, bookId = it.bookId)) }
            books[3]?.let { borrows.add(Borrow(userId = user.userId, bookId = it.bookId)) }
        }
        db.borrowDao().insertBorrows(borrows)
    }

    suspend fun borrowBookByUserName(userName: String, bookName: String): Boolean {
        val user = db.userDao().getUserByName(userName)
        val book = db.bookDao().getBookByName(bookName)

        return if (user != null && book != null) {
            db.borrowDao().insertBorrow(Borrow(userId = user.userId, bookId = book.bookId))
            true
        } else {
            false
        }
    }

    suspend fun getUserWithBooksByName(userName: String): UserWithBooks? {
        val user = db.userDao().getUserByName(userName)
        return if (user != null) {
            db.borrowDao().getUserWithBooks(user.userId)
        } else null
    }

    suspend fun getAllUsers(): List<User> {
        return db.userDao().getAllUsers()
    }

    suspend fun getAllBooks(): List<Book> {
        return db.bookDao().getAllBooks()
    }
}

