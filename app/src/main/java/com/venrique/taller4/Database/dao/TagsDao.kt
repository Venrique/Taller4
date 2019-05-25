package com.venrique.taller4.Database.dao

import android.nfc.Tag
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.venrique.taller4.Database.Entities.Tags

@Dao
interface TagsDao {

    @Insert
    suspend fun insert(tag: Tags)

    @Query("SELECT * from tags")
    fun getAllTags(): List<Tags>

    @Query("SELECT * from tags where Tid=:id")
    fun getOneTag(id: Int): Tags

    @Query("DELETE from tags")
    fun deleteAllTags()
}