package com.venrique.taller4.Database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.venrique.taller4.Database.Entities.Autor
import com.venrique.taller4.Database.Entities.Libro
import com.venrique.taller4.Database.Entities.LibroTagJoin
import com.venrique.taller4.Database.Entities.Tags
import com.venrique.taller4.Database.dao.AutorDao
import com.venrique.taller4.Database.dao.LibroDao
import com.venrique.taller4.Database.dao.LibroTagJoinDao
import com.venrique.taller4.Database.dao.TagsDao
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

@Database(entities = [Autor::class,Libro::class,Tags::class,LibroTagJoin::class],version = 1,exportSchema = false)
abstract class RoomDB: RoomDatabase(){
    abstract fun autorDao(): AutorDao
    abstract fun libroDao(): LibroDao
    abstract fun tagDao(): TagsDao
    abstract fun LibroTagJoinDao(): LibroTagJoinDao

    companion object {
        @Volatile
        private var INSTANCE: RoomDB? = null

        fun getDatabase(context: Context, scope: CoroutineScope): RoomDB {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    RoomDB::class.java,
                    "Book_database"
                ).addCallback(WordDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }

        private class WordDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch(Dispatchers.IO) {
                        populateDatabase(database.autorDao(),database.tagDao(),database.libroDao(),database.LibroTagJoinDao())
                    }
                }
            }

            suspend fun populateDatabase(autorDao: AutorDao,tagsDao: TagsDao,libroDao: LibroDao,libroTagJoinDao: LibroTagJoinDao) {
                autorDao.deleteAll()
                tagsDao.deleteAllTags()
                libroDao.deleteBook()
                libroTagJoinDao.deleteAllRel()

                var autor = Autor("Victor","El Salvador")
                var tag = Tags("fiction")

                autorDao.insert(autor)
                autor = Autor("Pedro","Canada")
                autorDao.insert(autor)

                tagsDao.insert(tag)
            }
        }
    }
}