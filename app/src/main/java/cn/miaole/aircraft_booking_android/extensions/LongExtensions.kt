package cn.miaole.aircraft_booking_android.extensions

import java.util.*

fun Long.toYMD(): String {
    val instance = Calendar.getInstance()
    instance.timeInMillis = this
    return "${instance.get(Calendar.YEAR)}" +
            "-${instance.get(Calendar.MONTH) + 1}" +
            "-${instance.get(Calendar.DAY_OF_MONTH)}"
}

fun String.toTimeStamp(): Long {
    val arr = this.split('-')
    val instance = Calendar.getInstance()
    instance.timeInMillis = 0
    instance.set(Calendar.YEAR, arr[0].toInt())
    instance.set(Calendar.MONTH, arr[1].toInt() - 1)
    instance.set(Calendar.DAY_OF_MONTH, arr[2].toInt())
    return instance.timeInMillis
}