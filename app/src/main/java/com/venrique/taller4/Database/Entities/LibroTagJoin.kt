package com.venrique.taller4.Database.Entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey

@Entity(tableName = "book_tag_join",foreignKeys = arrayOf(
    ForeignKey(entity = Libro::class,parentColumns = arrayOf("Bid"),childColumns = arrayOf("bookId")),
    ForeignKey(entity = Tags::class,parentColumns = arrayOf("Tid"),childColumns = arrayOf("tagId"))
), primaryKeys = arrayOf("bookId","tagId"))
data class LibroTagJoin(@ColumnInfo(name = "bookId") val bookId: Int,
                        @ColumnInfo(name = "tagId") val tagId: Int)