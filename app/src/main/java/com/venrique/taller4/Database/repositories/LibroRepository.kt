package com.venrique.taller4.Database.repositories

import android.nfc.Tag
import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.venrique.taller4.Database.Entities.Autor
import com.venrique.taller4.Database.Entities.Libro
import com.venrique.taller4.Database.Entities.LibroTagJoin
import com.venrique.taller4.Database.Entities.Tags
import com.venrique.taller4.Database.dao.AutorDao
import com.venrique.taller4.Database.dao.LibroDao
import com.venrique.taller4.Database.dao.LibroTagJoinDao
import com.venrique.taller4.Database.dao.TagsDao

class LibroRepository (private val autorDao: AutorDao,private val libroDao: LibroDao,private val libroTagJoinDao: LibroTagJoinDao,private val  tagsDao: TagsDao){



    //AUTORDAO
    @WorkerThread
    suspend fun insertautor(autor: Autor){
        autorDao.insert(autor)

    }

    fun deleteAllautor(){
        return autorDao.deleteAll()


    }

    fun deleteOneautor(id:Int){
        return autorDao.deleteOne(id)
    }

    fun selectAllautors(): List<Autor>{
        return autorDao.selectAll()
    }

    fun selectOneautor(id: Int): Autor{
        return autorDao.selectOne(id)
    }


    //LIBRODAO
    @WorkerThread
    suspend fun insertlibro(libro: Libro){
        libroDao.insert(libro)

    }

    fun getAllBooks(): LiveData<List<Libro>>{
        return libroDao.getAllBooks()
    }

    fun getBookByName(name:String): Libro{
        return libroDao.getBookByName(name)
    }

    fun getBookByAutor(autorId: Int): LiveData<List<Libro>>{
        return libroDao.getBookByAutor(autorId)
    }

    fun deleteBook(){
        libroDao.deleteBook()
    }

    //LibroTagJOinDao

    @WorkerThread
    suspend fun insert(libroTag: LibroTagJoin){
        libroTagJoinDao.insert(libroTag)
    }

    fun getBooksByTag(tagId: Int): List<LibroTagJoin>{
        return libroTagJoinDao.getBooksByTag(tagId)
    }


    //TagsDao

    @WorkerThread
    suspend fun insert(tag: Tags){
        tagsDao.insert(tag)
    }

    fun getAllTags(): List<Tags>{
        return tagsDao.getAllTags()
    }
    fun getOneTag(id: Int): Tags{
        return tagsDao.getOneTag(id)
    }
}