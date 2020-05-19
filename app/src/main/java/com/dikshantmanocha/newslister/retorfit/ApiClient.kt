package com.dikshantmanocha.newslister.retorfit

import com.dikshantmanocha.newslister.utils.NetworkUtils
import com.google.gson.GsonBuilder
import com.google.gson.internal.bind.util.ISO8601Utils
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import okhttp3.OkHttpClient
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException
import java.lang.reflect.Type
import java.util.*

object ApiClient {

    val BASE_URL = "https://newsapi.org/"
    val gson = GsonBuilder()
        .create()
    var retrofit: Retrofit? = null

    fun getClient(): Retrofit? {

        if (retrofit == null) {
            val client: OkHttpClient = NetworkUtils.getHttpClient()
            retrofit = Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addConverterFactory(QueryConverterFactory.Companion.create())
                .client(client)
                .build()
        }

        return retrofit
    }

    fun <T> getService(service: Class<T>?): T {
        val client = getClient()
        return client!!.create(service)
    }

    class QueryConverterFactory : Converter.Factory() {
        override fun stringConverter(
            type: Type,
            annotations: Array<Annotation>,
            retrofit: Retrofit
        ): Converter<*, String>? {
            return if (type === Date::class.java) {
                DateConverter.Companion.INSTANCE
            } else super.stringConverter(type, annotations, retrofit)
        }

        class DateConverter :
            Converter<Date?, String> {
            @Throws(IOException::class)
            override fun convert(value: Date?): String {
                return if (value == null) {
                    ""
                } else ISO8601Utils.format(value)
            }

            companion object {
                val INSTANCE =
                    DateConverter()
            }
        }

        companion object {
            fun create(): Converter.Factory {
                return QueryConverterFactory()
            }
        }
    }
}