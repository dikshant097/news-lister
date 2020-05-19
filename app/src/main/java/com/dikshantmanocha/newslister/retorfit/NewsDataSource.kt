package com.dikshantmanocha.newslister.retorfit

import com.dikshantmanocha.newslister.model.NewsModel
import io.reactivex.Observable


object NewsDataSource {

    var newsService: NewsService = ApiClient.getService(NewsService::class.java)

    fun getNewsList(country : String, apiKey : String): Observable<NewsModel> {
        return newsService.getNewsList(country, apiKey)
    }
}