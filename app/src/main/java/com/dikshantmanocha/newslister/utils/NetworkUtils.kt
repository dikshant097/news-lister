package com.dikshantmanocha.newslister.utils

import android.content.Context
import android.net.ConnectivityManager
import okhttp3.OkHttpClient
import java.util.concurrent.TimeUnit

object NetworkUtils {

    private val sClient: OkHttpClient = getHttpClient(30, 2 * 60 + 30, 60)

    fun getHttpClient(): OkHttpClient {
        return sClient
    }

    private fun  getHttpClient (connTimeout : Int, readTimeout: Int, writeTimeout: Int) : OkHttpClient {
        val builder = OkHttpClient.Builder()
        builder.connectTimeout(
            connTimeout.toLong(),
            TimeUnit.SECONDS
        ) // Timeout to create a connection
            .writeTimeout(
                writeTimeout.toLong(),
                TimeUnit.SECONDS
            ) // Write timeout
            .readTimeout(
                readTimeout.toLong(),
                TimeUnit.SECONDS
            ) // Timeout that the server supports

        return builder.build()
    }

    fun isNetworkAvailable(context: Context?): Boolean {
        if (context != null) {
            val connectivityManager =
                context.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
            val activeNetworkInfo = connectivityManager.activeNetworkInfo
            return activeNetworkInfo != null && activeNetworkInfo.isConnected
        }
        return false
    }
}