package com.example.bttuan_4.data.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Transaction
import com.example.bttuan_4.data.pojo.Borrow
import com.example.bttuan_4.data.pojo.UserWithBooks

@Dao
interface BorrowDao {
    @Insert
    suspend fun insertBorrow(borrows: Borrow)

    @Insert
    suspend fun insertBorrows(borrows: List<Borrow>)  //


    @Transaction
    @Query("SELECT * FROM users WHERE userId = :userId")
    suspend fun getUserWithBooks(userId: Int): UserWithBooks?
}