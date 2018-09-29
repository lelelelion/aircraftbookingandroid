package cn.miaole.aircraft_booking_android.model.internet.data

data class Airline(
    val name: String,
    val location: String,
    val id: String,
    val createdAt: Long,
    val updatedAt: Long,
    val version: Int
)