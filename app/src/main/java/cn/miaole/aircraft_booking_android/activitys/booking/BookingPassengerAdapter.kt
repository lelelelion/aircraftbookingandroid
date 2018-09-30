package cn.miaole.aircraft_booking_android.activitys.booking

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class BookingPassengerAdapter(list: MutableList<Passenger>) :
        BaseQuickAdapter<Passenger, BaseViewHolder>(R.layout.item_booking_passenger, list) {
    override fun convert(helper: BaseViewHolder?, item: Passenger?) {
        helper?.let { holder ->
            item?.let { item ->
                holder.setText(R.id.tvName, item.name)
            }
        }
    }
}