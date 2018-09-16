package cn.miaole.aircraft_booking_android.utils

import android.annotation.SuppressLint
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.location.LocationManager
import android.net.Uri
import android.provider.Settings
import com.orhanobut.logger.Logger
import org.jetbrains.anko.*

@SuppressLint("StaticFieldLeak")
object LocationUtil {

    // 保存定位管理者
    private var locationManager: LocationManager? = null
    private lateinit var context: Context

    fun bind(context: Context) {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        this.context = context
    }

    /**
     * 如果没有开启定位服务，则提示用户开启定位服务
     */
    fun ensureLocationServerOpen() {
        if(!isOpen())
            openGPS(context)
    }


    fun isOpen(): Boolean {
        val gps = locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false
        val network = locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ?: false
        return gps || network
    }

    fun openGPS(context: Context) {
        context.alert("未开启定位，是否跳转到开启定位界面") {
            yesButton {
                Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS)
                        .apply {
                            context.startActivity(this)
                        }
            }
            noButton { }
        }.show()
    }

}