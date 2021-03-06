package cn.miaole.aircraft_booking_android.activitys.my_order

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.model.internet.data.Order
import cn.miaole.aircraft_booking_android.utils.DateUtil
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import java.util.*

class MyOrderAdapter(list: MutableList<Order>) :
        BaseQuickAdapter<Order, BaseViewHolder>(R.layout.item_order, list) {
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
                        .setText(R.id.tvTotalPrice,
                                mContext.getString(R.string.total_price, item.cost.toString()))
                when(item.status){
                    0 -> {
                        holder.setVisible(R.id.tvStatus, false)
                    }
                    2 -> {
                        holder.setVisible(R.id.tvStatus, true)
                        holder.setText(R.id.tvStatus, R.string.already_return)
                    }
                    1 -> {
                        holder.setVisible(R.id.tvStatus, true)
                        holder.setText(R.id.tvStatus, R.string.already_finish)
                    }
                    else -> {
                    }
                }
            }
        }
    }
}