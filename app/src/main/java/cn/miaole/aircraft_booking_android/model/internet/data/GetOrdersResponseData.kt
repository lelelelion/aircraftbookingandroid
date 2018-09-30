package cn.miaole.aircraft_booking_android.model.internet.data


data class GetOrdersResponseData(
    val contactName: String,
    val phone: String,
    val email: String,
    val status: Int,
    val cost: Int,
    val id: String,
    val createdAt: Long,
    val updatedAt: Long,
    val version: Int,
    val uid: String,
    val ticketId: String,
    val passengers: List<MyPassenger>,
    val Ticket: MyTicket
)

data class MyPassenger(
    val name: String,
    val certificateType: String,
    val certificateValue: String,
    val birthday: Int,
    val phone: String,
    val email: String,
    val isAdult: Boolean,
    val id: String,
    val createdAt: Long,
    val updatedAt: Long,
    val version: Int,
    val uid: String,
    val Order_Passenger: OrderPassenger
)

data class OrderPassenger(
    val createdAt: String,
    val updatedAt: String,
    val orderId: String,
    val passengerContactId: String
)

data class MyTicket(
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
    val flightId: String,
    val Flight: Flight
)

