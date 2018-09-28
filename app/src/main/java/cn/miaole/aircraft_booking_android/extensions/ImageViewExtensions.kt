package cn.miaole.aircraft_booking_android.extensions

import android.support.annotation.DrawableRes
import android.widget.ImageView
import com.j.ming.dcim.GlideApp

fun ImageView.loadCircle(url: String) {
    GlideApp.with(this)
            .load(url)
            .circleCrop()
            .into(this)
}

fun ImageView.loadCircle(@DrawableRes res: Int) {
    GlideApp.with(this)
            .load(res)
            .circleCrop()
            .into(this)
}