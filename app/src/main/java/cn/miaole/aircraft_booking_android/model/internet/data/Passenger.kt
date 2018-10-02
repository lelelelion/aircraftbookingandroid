package cn.miaole.aircraft_booking_android.model.internet.data

data class Passenger(
    val name: String,
    val certificateType: Int,
    val certificateValue: String,
    val birthday: Int,
    val phone: String,
    val email: String,
    val id: String,
    val createdAt: Long,
    val updatedAt: Long,
    val version: Int,
    val uid: String,
    val isAdult: Boolean
)