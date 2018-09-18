package cn.miaole.aircraft_booking_android.utils

import android.annotation.SuppressLint
import android.content.Context
import android.content.Intent
import android.location.Location
import android.location.LocationListener
import android.location.LocationManager
import android.os.Bundle
import android.provider.Settings
import cn.miaole.aircraft_booking_android.extensions.checkMyPermission
import com.orhanobut.logger.Logger
import org.jetbrains.anko.*

@SuppressLint("StaticFieldLeak")
object LocationUtil {

    // 保存定位管理者
    private var locationManager: LocationManager? = null
    private lateinit var context: Context
    private const val minTime = 2000L
    private const val minDistance = 2f
    private val onLocationChangeListeners = mutableListOf<OnLocationChangeListener>()

    fun bind(context: Context) {
        locationManager = context.getSystemService(Context.LOCATION_SERVICE) as LocationManager
        this.context = context
    }

    /**
     * 如果没有开启定位服务，则提示用户开启定位服务
     */
    fun ensureLocationServerOpen() {
        if (!isOpen())
            openGPS(context)
    }

    /**
     * 判断当前是否开启定位
     */
    fun isOpen(): Boolean {
        val gps = locationManager?.isProviderEnabled(LocationManager.GPS_PROVIDER) ?: false
        val network = locationManager?.isProviderEnabled(LocationManager.NETWORK_PROVIDER) ?: false
        return gps || network
    }

    /**
     * 弹窗提示用户打开定位
     */
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

    /**
     * 获取最后最近一次的定位信息
     */
    fun getLastLocation(provider: String): Location? {
        var location: Location? = null
        context.checkMyPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION, {
            Logger.i(provider)
            location = locationManager?.getLastKnownLocation(provider)
                    .let {
                        Logger.i(location?.easyToJson() ?: "wtf")
                        location
                    }
        })
        return location
    }


    /**
     * begin listen location change
     */
    fun beginListen() {
        context.checkMyPermission(android.Manifest.permission.ACCESS_COARSE_LOCATION)

        // gps
        locationManager?.requestLocationUpdates(LocationManager.GPS_PROVIDER, minTime, minDistance,
                object : LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        onLocationChangeListeners.forEach {
                            location?.apply {
                                it.onGpsLocationChange(this)
                            }
                        }
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    }

                    override fun onProviderEnabled(provider: String?) {
                    }

                    override fun onProviderDisabled(provider: String?) {
                    }

                })

        // network
        locationManager?.requestLocationUpdates(LocationManager.NETWORK_PROVIDER, minTime, minDistance,
                object : LocationListener {
                    override fun onLocationChanged(location: Location?) {
                        onLocationChangeListeners.forEach {
                            location?.apply {
                                it.onNetworkLocationChange(this)
                            }
                        }
                    }

                    override fun onStatusChanged(provider: String?, status: Int, extras: Bundle?) {
                    }

                    override fun onProviderEnabled(provider: String?) {
                    }

                    override fun onProviderDisabled(provider: String?) {
                    }

                })
    }


    fun addOnLocationChangeListener(onLocationChangeListener: OnLocationChangeListener) {
        onLocationChangeListeners.add(onLocationChangeListener)
    }

    fun removeOnLocationChangeListion(onLocationChangeListener: OnLocationChangeListener) {
        onLocationChangeListeners.remove(onLocationChangeListener)
    }

    fun clearOnLocationChangeListener() {
        onLocationChangeListeners.clear()
    }

    interface OnLocationChangeListener {
        fun onGpsLocationChange(location: Location)

        fun onNetworkLocationChange(location: Location)
    }

}