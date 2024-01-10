package com.dennytech.domain.utils

import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale

object Helpers {
    fun dateStringToMillis(timestamp: String): Long {
        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = inputFormat.parse(timestamp)

        return date?.time ?: 0L
    }

    fun addDaysToDate(timestamp: String, daysToAdd: Int): Long {

        val inputFormat = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        val date = inputFormat.parse(timestamp)

        val calendar = Calendar.getInstance()
        calendar.time = date!!

        calendar.add(Calendar.DAY_OF_MONTH, daysToAdd)

        return calendar.time.time
    }

    fun longToString(date: Long): String {
        val sdf = SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.getDefault())
        return sdf.format(date)
    }

    fun addDaysToTimestamp(timestamp: String, daysToAdd: Int) : String {
        val endDateLong = addDaysToDate(timestamp, daysToAdd)
       return longToString(endDateLong)
    }
}