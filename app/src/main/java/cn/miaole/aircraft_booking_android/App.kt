package cn.miaole.aircraft_booking_android

import android.app.Application
import cn.miaole.aircraft_booking_android.model.ABAApi
import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.utils.LocationUtil
import com.mob.MobSDK
import com.orhanobut.logger.AndroidLogAdapter
import com.orhanobut.logger.Logger

class App : Application() {
    companion object {
        const val APP_NAME = "ABA"
    }

    override fun onCreate() {
        super.onCreate()

        //init Logger
        Logger.addLogAdapter(AndroidLogAdapter())

        //API Client
        APIManager.init(this)
        ABAApi.bind(this)

        //init Mob SMSSDK
        MobSDK.init(this)
    }
}