package com.example.bttuan_4.data.pojo

import androidx.room.Embedded
import androidx.room.Entity
import androidx.room.Junction
import androidx.room.Relation

@Entity(
    tableName = "borrow",
    primaryKeys = ["userId", "bookId"]
)

data class Borrow(
    val userId: Int,
    val bookId: Int
)

data class UserWithBooks(
    @Embedded val user: User,
    @Relation(
        parentColumn = "userId",
        entityColumn = "bookId",
        associateBy = Junction(Borrow::class)
    )
    val books: List<Book>
)