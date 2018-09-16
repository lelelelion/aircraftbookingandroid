package cn.miaole.aircraft_booking_android.utils

import android.content.Context
import java.io.BufferedReader
import java.io.InputStreamReader

object CityNamesUtil {
    fun init(context: Context, fileName: String): String {
        val sb = StringBuilder()
        context.assets.apply {
            BufferedReader(InputStreamReader(open(fileName)))
                    .readLines()
                    .forEach {
                        sb.append(it)
                    }
        }
        return sb.toString()
    }


}