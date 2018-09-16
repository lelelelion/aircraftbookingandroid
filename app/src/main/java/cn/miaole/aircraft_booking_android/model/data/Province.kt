package cn.miaole.aircraft_booking_android.model.data

import java.util.*

data class Province(val provinceName: String, val cities: Array<City>) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Province

        if (provinceName != other.provinceName) return false
        if (!Arrays.equals(cities, other.cities)) return false

        return true
    }

    override fun hashCode(): Int {
        var result = provinceName.hashCode()
        result = 31 * result + Arrays.hashCode(cities)
        return result
    }
}