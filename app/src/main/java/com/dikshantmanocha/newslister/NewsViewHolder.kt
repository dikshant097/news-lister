package com.dikshantmanocha.newslister

import android.content.Context
import android.content.Intent
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.dikshantmanocha.newslister.model.ArticleModel
import com.dikshantmanocha.newslister.utils.Utility

class NewsViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

    fun setUi(articleModel: ArticleModel) {
        val title = itemView.findViewById<TextView>(R.id.title)
        val description = itemView.findViewById<TextView>(R.id.description)
        val readFurther = itemView.findViewById<TextView>(R.id.read_further)
        val image = itemView.findViewById<ImageView>(R.id.image)
        val date = itemView.findViewById<TextView>(R.id.date)
        val context: Context = itemView.context

        Utility.setHtmlText(title, articleModel.title)
        Utility.setHtmlText(description, articleModel.description)
        Utility.setHtmlText(
            date,
            Utility.getDateTimeInGivenFormat("dd-MMM, hh:mm", articleModel.timeStamp)
        )

        itemView.setOnClickListener {
            val intent = Intent(context, FullArticleActivity::class.java)
            intent.putExtra("article", articleModel)
            context.startActivity(intent)
        }

        if (Utility.isStringValid(articleModel.image)) {
            Glide.with(context)
                .load(articleModel.image)
                .centerCrop()
                .into(image)
        }
    }
}