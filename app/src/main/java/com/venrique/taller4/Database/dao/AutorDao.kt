package com.venrique.taller4.Database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.venrique.taller4.Database.Entities.Autor
import com.venrique.taller4.Database.Entities.Libro

@Dao
interface AutorDao {

    @Insert
    suspend fun insert(autor: Autor)

    @Query("DELETE from autor")
    fun deleteAll()

    @Query("DELETE from autor where Aid=:id")
    fun deleteOne(id:Int)

    @Query("SELECT * from autor")
    fun selectAll(): List<Autor>

    @Query("SELECT * from autor where Aid=:id")
    fun selectOne(id: Int): Autor
}