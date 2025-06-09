package com.example.bttuan_4.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.example.bttuan_4.data.dao.BookDao
import com.example.bttuan_4.data.dao.BorrowDao
import com.example.bttuan_4.data.dao.UserDao
import com.example.bttuan_4.data.pojo.Book
import com.example.bttuan_4.data.pojo.Borrow
import com.example.bttuan_4.data.pojo.User

@Database(entities = [User::class, Book::class, Borrow::class], version = 5)
abstract class LibraryDatabase : RoomDatabase() {
    abstract fun userDao(): UserDao
    abstract fun bookDao(): BookDao
    abstract fun borrowDao(): BorrowDao

    companion object {
        @Volatile
        private var INSTANCE: LibraryDatabase? = null

        fun getDatabase(context: Context): LibraryDatabase {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    LibraryDatabase::class.java,
                    "library_database"
                )
                    .fallbackToDestructiveMigration()
                    .build()
                INSTANCE = instance
                instance
            }
        }
    }
}
