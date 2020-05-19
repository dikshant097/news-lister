package com.dikshantmanocha.newslister

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.View
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.dikshantmanocha.newslister.model.NewsModel
import com.dikshantmanocha.newslister.retorfit.NewsDataSource
import com.dikshantmanocha.newslister.room.ArticleDatabase
import com.dikshantmanocha.newslister.utils.Constants.COUNTRY_CODE
import com.dikshantmanocha.newslister.utils.Constants.NEWS_API_KEY
import com.dikshantmanocha.newslister.utils.NetworkUtils
import com.dikshantmanocha.newslister.utils.Utility
import io.reactivex.Observable
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        getNews()
    }

    @SuppressLint("CheckResult")
    private fun getNews() {
        progress_bar.visibility = View.VISIBLE

        if (NetworkUtils.isNetworkAvailable(this)) {

            NewsDataSource.getNewsList(COUNTRY_CODE, NEWS_API_KEY)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(this::setNews) {
                    Utility.showToast(this, getString(R.string.error_msg))
                    progress_bar.visibility = View.GONE
                    it.printStackTrace()
                }

        } else {

            ArticleDatabase.getInstance()
                .newsDao()
                .getArticles()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe({
                    progress_bar.visibility = View.GONE

                    if (it.size == 0) {
                        Utility.showToast(this, getString(R.string.no_results_in_cache))
                    }

                    val newsAdapter = NewsAdapter(it)
                    val layoutManager = LinearLayoutManager(this)
                    news_list.layoutManager = layoutManager
                    news_list.adapter = newsAdapter
                    news_list.visibility = View.VISIBLE

                }, {
                    Utility.showToast(this, getString(R.string.error_msg))
                    progress_bar.visibility = View.GONE
                    it.printStackTrace()
                })

        }
    }

    private fun setNews(newsModel: NewsModel) {
        if (isDestroyed || newsModel.articles == null) {
            return
        }

        val newsDao = ArticleDatabase.getInstance()
            .newsDao()

        val newsAdapter = NewsAdapter(newsModel.articles!!)
        val layoutManager = LinearLayoutManager(this)
        news_list.layoutManager = layoutManager
        news_list.adapter = newsAdapter
        news_list.visibility = View.VISIBLE
        progress_bar.visibility = View.GONE

        Observable.fromCallable({
            newsDao.insertArticles(newsModel.articles!!)
        }).subscribeOn(Schedulers.computation())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe()


    }
}
