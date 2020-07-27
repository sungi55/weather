package com.sunhurov.common.utils

import android.annotation.SuppressLint
import java.text.ParseException
import java.text.SimpleDateFormat
import java.time.Instant
import java.time.format.DateTimeFormatter
import java.time.temporal.TemporalAccessor
import java.util.*

/**
 * Created by Yurii Sunhurov on 26.07.2020
 */
object DateUtils {
    @SuppressLint("SimpleDateFormat")
    fun getLocalStringFormat(stringDate: String?): String? {
        var finalString = stringDate
        try {

            val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ssZ", Locale.ENGLISH)
            val formatter = SimpleDateFormat("HH:mm")

             finalString = stringDate
                ?.let { parser.parse(it) }
                ?.let { formatter.format(it) }

        } catch (e: ParseException) {
            e.printStackTrace()
        }
        return finalString
    }
}