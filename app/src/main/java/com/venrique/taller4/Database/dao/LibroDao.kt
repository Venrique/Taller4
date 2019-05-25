package com.venrique.taller4.Database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.venrique.taller4.Database.Entities.Libro

@Dao
interface LibroDao {

    @Insert
    suspend fun insert(libro: Libro)

    @Query("SELECT * from book")
    fun getAllBooks(): LiveData<List<Libro>>

    @Query("SELECT * FROM book where book_name=:name")
    fun getBookByName(name:String): Libro

    @Query("SELECT * from book where autor_id=:autorId")
    fun getBookByAutor(autorId: Int): LiveData<List<Libro>>

    @Query("DELETE from book")
    fun deleteBook()
}