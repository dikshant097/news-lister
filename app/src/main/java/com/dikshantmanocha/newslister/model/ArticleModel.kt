package com.dikshantmanocha.newslister.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.dikshantmanocha.newslister.room.TypeConverter
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.util.*

@Parcelize
@Entity(tableName = "articles")
@TypeConverters(TypeConverter::class)
data class ArticleModel(
    @ColumnInfo(name = "author") @SerializedName("author") var author: String?,
    @ColumnInfo(name = "title") @SerializedName("title") var title: String?,
    @ColumnInfo(name = "description") @SerializedName("description") var description: String?,
    @ColumnInfo(name = "url") @SerializedName("url") var url: String?,
    @ColumnInfo(name = "urlToImage") @SerializedName("urlToImage") var image: String?,
    @PrimaryKey @ColumnInfo(name = "publishedAt") @SerializedName("publishedAt") var timeStamp: Date?,
    @ColumnInfo(name = "content") @SerializedName("content") var content: String?
) : Parcelable {

}