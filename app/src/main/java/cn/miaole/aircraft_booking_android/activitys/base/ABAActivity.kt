package cn.miaole.aircraft_booking_android.activitys.base

import android.os.Build
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import cn.miaole.aircraft_booking_android.R

abstract class ABAActivity: AppCompatActivity(){
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // 统一设置所有状态栏的颜色
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            window.statusBarColor = resources.getColor(R.color.colorPrimary)
        }
    }
}