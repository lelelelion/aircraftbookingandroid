package cn.miaole.aircraft_booking_android.model.internet.data

data class Aircraft(
    val flightNumber: String,
    val capacity: Int,
    val id: String,
    val createdAt: Long,
    val updatedAt: Long,
    val version: Int,
    val companyId: String
)