package cn.miaole.aircraft_booking_android.model.internet.data

data class LocationInfo(
    val status: Int,
    val result: Result
)

data class Result(
    val location: Location,
    val formatted_address: String,
    val business: String,
    val addressComponent: AddressComponent,
    val pois: List<Any>,
    val roads: List<Any>,
    val poiRegions: List<Any>,
    val sematic_description: String,
    val cityCode: Int
)

data class AddressComponent(
    val country: String,
    val country_code: Int,
    val country_code_iso: String,
    val country_code_iso2: String,
    val province: String,
    val city: String,
    val city_level: Int,
    val district: String,
    val town: String,
    val adcode: String,
    val street: String,
    val street_number: String,
    val direction: String,
    val distance: String
)

data class Location(
    val lng: Double,
    val lat: Double
)