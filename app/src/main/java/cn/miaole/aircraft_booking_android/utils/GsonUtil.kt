package cn.miaole.aircraft_booking_android.utils

import cn.miaole.aircraft_booking_android.model.data.Province
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

object GsonUtil {
    val instance = Gson()
}

fun <T> String.easyToObj(clazz: Class<T>): T {
    return GsonUtil.instance.fromJson(this, clazz)
}

fun <T> String.easyToList(clazz: Class<T>): List<T> {
    val type = object : TypeToken<List<T>>() {}.type
    return GsonUtil.instance.fromJson(this, type)
}

fun Any.easyToJson(): String {
    return GsonUtil.instance.toJson(this)
}

