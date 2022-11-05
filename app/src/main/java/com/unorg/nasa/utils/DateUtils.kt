package com.unorg.nasa.utils

import java.text.SimpleDateFormat

object DateUtils {

    @JvmStatic
    fun getDate(dateString: String): String {//2006-11-17
        return try {
            val date = SimpleDateFormat("yyyy-mm-dd").parse(dateString)
            if (date == null) "N/A"
            else
                SimpleDateFormat("dd MMM yyyy").format(date)
        } catch (e: Exception) {
            "N/A"
        }
    }

}