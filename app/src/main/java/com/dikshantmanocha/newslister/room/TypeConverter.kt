package com.dikshantmanocha.newslister.room

import androidx.room.TypeConverter
import java.util.*

class TypeConverter {

    @androidx.room.TypeConverter
    fun toDate(dateLong: Long): Date {
        return Date(dateLong)
    }

    @TypeConverter
    fun fromDate(date: Date): Long {
        return date.time;
    }
}