package cn.miaole.aircraft_booking_android.extensions

import java.util.*

fun Long.toYMD(): String{
    val instance = Calendar.getInstance()
    instance.timeInMillis = this
    return "${instance.get(Calendar.YEAR)}" +
            "-${instance.get(Calendar.MONTH) + 1}" +
            "-${instance.get(Calendar.DAY_OF_MONTH)}"
}