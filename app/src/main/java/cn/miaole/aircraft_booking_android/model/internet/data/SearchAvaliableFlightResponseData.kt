package cn.miaole.aircraft_booking_android.model.internet.data


data class SearchAvaliableFlightResponseData(
        val distance: String,
        val punctuality: String,
        val duration: String,
        val departTime: String,
        val arrivalTime: String,
        val fromTerminal: String,
        val toTerminal: String,
        val week: String,
        val id: String,
        val createdAt: Long,
        val updatedAt: Long,
        val version: Int,
        val companyId: String,
        val aircraftId: String,
        val departAirportId: String,
        val arrivalAirportId: String,
        val departAirport: Airport,
        val arrivalAirport: Airport,
        val airline: Airline,
        val aircraft: Aircraft,
        val tickets: List<Ticket>
)