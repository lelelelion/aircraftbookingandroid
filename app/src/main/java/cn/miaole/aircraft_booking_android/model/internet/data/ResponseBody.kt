package cn.miaole.aircraft_booking_android.model.internet.data

data class ResponseBody<out T>(val success: Boolean = false, val code: Int, val msg: String = "",
                               val data: T? = null)