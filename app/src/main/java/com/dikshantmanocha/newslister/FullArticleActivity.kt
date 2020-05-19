package com.dikshantmanocha.newslister

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.bumptech.glide.Glide
import com.dikshantmanocha.newslister.model.ArticleModel
import com.dikshantmanocha.newslister.utils.Utility
import kotlinx.android.synthetic.main.activity_full_article.*

class FullArticleActivity : AppCompatActivity() {

    var article: ArticleModel? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_full_article)

        handleIntent()
        init()
    }

    private fun init() {
        if (article == null) {
            finish()
            return
        }

        Utility.setHtmlText(titleTV, article!!.title)
        Utility.setHtmlText(description, article!!.description)
        Utility.setHtmlText(content, article!!.content)
        Utility.setHtmlText(
            date,
            Utility.getDateTimeInGivenFormat("dd-MMM-YYYY, hh:mm:ss", article!!.timeStamp)
        )

        Glide.with(this)
            .load(article!!.image)
            .centerCrop()
            .into(image)

    }

    private fun handleIntent() {
        val intent = intent;

        if (intent == null) {
            finish()
            return
        }

        article = intent.getParcelableExtra("article")

        if (article == null) {
            finish()
        }
    }
}
