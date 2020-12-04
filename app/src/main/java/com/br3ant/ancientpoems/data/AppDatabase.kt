package com.br3ant.ancientpoems.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.br3ant.ancientpoems.data.dao.AuthorDao
import com.br3ant.ancientpoems.data.dao.SongCiDao
import com.br3ant.ancientpoems.data.entities.Author
import com.br3ant.ancientpoems.data.entities.SongCi

/**
 * <pre>
 * copyright: datedu
 * @author : br3ant
 * e-mail : xxx@xx
 * time   : 12/4/20
 * desc   :
 * version: 1.0
</pre> *
 */
@Database(
    entities = [
        SongCi::class,
        Author::class
    ],
    version = 1,
    exportSchema = false
)

abstract class AppDatabase : RoomDatabase() {

    companion object {

        private const val DATABASE_NAME = "ancientPoems.db"

        fun createDatabase(context: Context): AppDatabase {
            return Room.databaseBuilder(context, AppDatabase::class.java, DATABASE_NAME)
                .fallbackToDestructiveMigration()
                .build()
        }
    }

    abstract fun songCiDao(): SongCiDao

    abstract fun authorDao(): AuthorDao
}