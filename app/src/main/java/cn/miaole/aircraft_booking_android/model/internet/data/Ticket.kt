package cn.miaole.aircraft_booking_android.model.internet.data

data class Ticket(
    val level: Int,
    val price: Double,
    val discount: Double,
    val standbyTicket: Int,
    val effectDate: Long,
    val expireDate: Long,
    val id: String,
    val createdAt: Long,
    val updatedAt: Long,
    val version: Int,
    val flightId: String
)