package cn.miaole.aircraft_booking_android.model.internet.data

data class User(
        val username: String,
        val avatar: String,
        val nickname: String,
        val gender: Int,
        val phone: String,
        val email: String,
        val money: Int,
        val id: String,
        val createdAt: Long,
        val updatedAt: Long,
        val version: Int
)