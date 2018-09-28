package cn.miaole.aircraft_booking_android.extensions

/**
 * Created by sunny on 17-12-29.
 */

fun easySin(angle: Float, radius:Int = 1): Double {
    return Math.sin(Math.PI * angle / 180) * radius
}

fun easyCos(angle: Float, radius: Int = 1): Double {
    return Math.cos(Math.PI * angle / 180) * radius
}