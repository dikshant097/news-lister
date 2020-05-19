package com.dikshantmanocha.newslister.room

import android.content.Context
import android.util.Log
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.sqlite.db.SupportSQLiteDatabase
import com.dikshantmanocha.newslister.model.ArticleModel


@Database(entities = arrayOf(ArticleModel::class), version = 2)
abstract class ArticleDatabase : RoomDatabase() {

    companion object {

        val DB_NAME = "articles.db"
        private lateinit var DB_INSTANCE: ArticleDatabase

        fun createDatabase(context: Context) {
            DB_INSTANCE = Room.databaseBuilder(
                context.applicationContext,
                ArticleDatabase::class.java,
                DB_NAME
            )
                .addCallback(object : Callback() {
                    override fun onCreate(db: SupportSQLiteDatabase) {
                        super.onCreate(db)
                        Log.d(ArticleDatabase::class.java.name, "DB Created")
                    }
                })
                .build()
        }

        fun getInstance(): ArticleDatabase {
            return DB_INSTANCE
        }
    }

    abstract fun newsDao(): NewsDao
}