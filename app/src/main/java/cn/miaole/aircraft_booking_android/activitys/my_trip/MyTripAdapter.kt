package cn.miaole.aircraft_booking_android.activitys.my_trip

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.model.internet.data.Order
import cn.miaole.aircraft_booking_android.utils.DateUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import java.util.*

class MyTripAdapter(list: List<Order>): BaseQuickAdapter<Order, BaseViewHolder>(R.layout.item_trip,
        list){
    override fun convert(helper: BaseViewHolder?, item: Order?) {
        helper?.let { holder ->
            item?.let { item ->
                val instance = Calendar.getInstance()
                instance.timeInMillis = item.Ticket.effectDate
                val flightInfo = item.Ticket.Flight
                holder
                        .addOnClickListener(R.id.tvDeleteOrder)
                        .addOnClickListener(R.id.tvTotalPrice)
                        .setText(R.id.tvFlightNumberAndDateAndWeek,
                                mContext.getString(R.string.aircraft_number_and_date_and_week,
                                        flightInfo.aircraft.flightNumber, "${instance.get(Calendar.YEAR)}" +
                                        "-${instance.get(Calendar.MONTH) + 1}" +
                                        "-${instance.get(Calendar.DAY_OF_MONTH)}",
                                        DateUtil.getChineseWeek(instance.get(Calendar.DAY_OF_WEEK))))
                        .setText(R.id.tvTurnOffTime, flightInfo.departTime)
                        .setText(R.id.tvArriveTime, flightInfo.arrivalTime)
                        .setText(R.id.tvFromAirport, "${flightInfo.departAirport.name}${flightInfo.fromTerminal}")
                        .setText(R.id.tvToAirport, "${flightInfo.arrivalAirport.name}${flightInfo.toTerminal}")
                        .setText(R.id.tvDuration, flightInfo.duration)
            }
        }
    }

}