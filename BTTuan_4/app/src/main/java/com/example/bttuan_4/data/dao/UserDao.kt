package com.example.bttuan_4.data.dao

import androidx.room.*
import com.example.bttuan_4.data.pojo.User

@Dao
interface UserDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertUsers(user: User)

    @Query("SELECT * FROM users")
    suspend fun getAllUsers(): List<User>

    @Query("SELECT * FROM users WHERE userName = :name LIMIT 1")
    suspend fun getUserByName(name: String): User?

}