package com.example.visionmemo.converter

import androidx.room.TypeConverter
import androidx.room.TypeConverters
import java.util.Date

class Converter {
    @TypeConverter
    fun dateToLong(value: Date):Long{
        return value.time
    }

    @TypeConverter
    fun longToDate(value:Long):Date{
        return Date(value)
    }
}