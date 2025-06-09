package com.example.bttuan_4.data.pojo

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName =  "books")
data class Book(
    @PrimaryKey(autoGenerate = true) val bookId: Int = 0,
    val name: String,
)