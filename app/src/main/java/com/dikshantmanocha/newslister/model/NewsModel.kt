package com.dikshantmanocha.newslister.model

import com.google.gson.annotations.SerializedName

data class NewsModel(
    @SerializedName("articles") var articles: ArrayList<ArticleModel>?,
    @SerializedName("totalResults") var TotalResults: Int,
    @SerializedName("status") var status: String
) {

}