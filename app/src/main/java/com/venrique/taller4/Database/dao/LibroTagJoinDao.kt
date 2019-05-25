package com.venrique.taller4.Database.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.venrique.taller4.Database.Entities.LibroTagJoin

@Dao
interface LibroTagJoinDao {

    @Insert
    suspend fun insert(libroTag: LibroTagJoin)

    @Query("SELECT * from book INNER JOIN book_tag_join ON book.Bid= book_tag_join.bookId WHERE book_tag_join.tagId=:tagId")
    fun getBooksByTag(tagId: Int): List<LibroTagJoin>

    @Query("DELETE from book_tag_join")
    fun deleteAllRel()
}