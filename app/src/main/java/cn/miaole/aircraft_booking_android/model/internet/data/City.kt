package cn.miaole.aircraft_booking_android.model.internet.data


data class City(
    val code: String,
    val name: String,
    val spell: String,
    val id: String,
    val createdAt: Long,
    val updatedAt: Long,
    val version: Int
)