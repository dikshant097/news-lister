package com.dikshantmanocha.newslister.room

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.dikshantmanocha.newslister.model.ArticleModel
import io.reactivex.Observable


@Dao
interface NewsDao {

    @Query("SELECT * from articles order by publishedAt desc")
    fun getArticles(): Observable<List<ArticleModel>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertArticles(articles: List<ArticleModel>)
}