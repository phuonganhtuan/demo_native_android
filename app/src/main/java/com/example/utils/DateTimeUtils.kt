package com.example.utils

import java.text.SimpleDateFormat
import java.util.*

object DateTimeUtils {

    fun getDateString(
        date: Date, format: String = "dd/MM/yyyy"
    ): String {
        val dateFormat = SimpleDateFormat(format)
        return dateFormat.format(date)
    }
}
