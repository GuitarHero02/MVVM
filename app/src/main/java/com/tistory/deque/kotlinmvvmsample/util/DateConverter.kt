package com.tistory.deque.kotlinmvvmsample.util

import androidx.room.TypeConverter
import java.util.*

/**
 * @author Leopold
 */
class DateConverter {
    @TypeConverter
    fun toDate(value: Long): Date = Date(value)

    @TypeConverter
    fun toLong(date: Date): Long = date.time
}