package cn.miaole.aircraft_booking_android.utils

object DateUtil{
    fun getChineseWeek(week: Int): String {
        return when (week) {
            1 -> "日"
            2 -> "一"
            3 -> "二"
            4 -> "三"
            5 -> "四"
            6 -> "五"
            7 -> "六"
            else -> {
                "一"
            }
        }
    }
}