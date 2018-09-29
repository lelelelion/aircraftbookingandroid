package cn.miaole.aircraft_booking_android.model.internet.data

data class Airport(
    val name: String,
    val threeCode: String,
    val id: String,
    val createdAt: Long,
    val updatedAt: Long,
    val version: Int,
    val cityId: String
)