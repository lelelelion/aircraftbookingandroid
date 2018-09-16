package cn.miaole.aircraft_booking_android.views.easy_refresh

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import cn.miaole.aircraft_booking_android.R

/**
 * Created by sunny on 18-2-27.
 */
class WebHeader: EasyRefreshHeaderHandler(R.layout.web_header) {

    private var context: Context? = null
    override fun getView(inflater: LayoutInflater?, viewGroup: ViewGroup?) {
        context = inflater?.context
        inflater?.inflate(headerResourceId, viewGroup, true)
    }

    override fun scrolling(header: View?, scrollDistance: Int, totalHeaderHeight: Int) {
    }

    override fun init(header: View?) {
    }

    override fun refreshing(header: View?) {
    }

    override fun refreshFinish(header: View?) {
    }

}