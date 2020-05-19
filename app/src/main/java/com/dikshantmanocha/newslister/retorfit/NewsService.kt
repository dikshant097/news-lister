package com.dikshantmanocha.newslister.retorfit

import com.dikshantmanocha.newslister.model.NewsModel
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface NewsService {

    @GET("v2/top-headlines")
    fun getNewsList(@Query("country") country : String, @Query("apiKey") apiKey : String): Observable<NewsModel>
}