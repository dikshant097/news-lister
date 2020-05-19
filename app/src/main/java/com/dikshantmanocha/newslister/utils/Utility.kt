package com.dikshantmanocha.newslister.utils

import android.app.Activity
import android.os.Build
import android.text.Html
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.*

object Utility {

    fun setHtmlText(textView: TextView, text: String?) {
        if (isStringValid(text)) {
            textView.visibility = View.VISIBLE
        } else {
            textView.visibility = View.GONE
            return
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            textView.text = Html.fromHtml(text, Html.FROM_HTML_MODE_COMPACT)
        } else {
            textView.text = Html.fromHtml(text)
        }
    }


    fun isStringValid(stringToValidate: String?): Boolean {
        if (stringToValidate == null) {
            return false
        }
        val trimmedStringToValidate: String
        trimmedStringToValidate = stringToValidate.trim { it <= ' ' }
        return (!trimmedStringToValidate.isEmpty()
                && !trimmedStringToValidate.equals("null", ignoreCase = true)
                && !trimmedStringToValidate.equals("None", ignoreCase = true)
                && !trimmedStringToValidate.equals("na", ignoreCase = true))
    }

    fun getDateTimeInGivenFormat(
        format: String,
        date: Date?
    ): String? {
        if (date == null) {
            return ""
        }

        val dateFormat: DateFormat = SimpleDateFormat(format)
        return dateFormat.format(date)
    }

    fun showToast(activity: Activity, message: String?) {

        if (!isStringValid(message) || activity.isFinishing || activity.isDestroyed) {
            return
        }

        Toast.makeText(activity, message, Toast.LENGTH_SHORT).show()


    }
}