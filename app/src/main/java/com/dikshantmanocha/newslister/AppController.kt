package com.dikshantmanocha.newslister

import android.app.Application
import com.dikshantmanocha.newslister.room.ArticleDatabase

class AppController : Application() {

    override fun onCreate() {
        super.onCreate()

        //Initializing Database
        ArticleDatabase.createDatabase(applicationContext)
    }
}