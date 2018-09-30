package cn.miaole.aircraft_booking_android.activitys.ticket_search_result

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.extensions.toFixed
import cn.miaole.aircraft_booking_android.model.internet.data.SearchAvaliableFlightResponseData
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class TicketSearchResultAdapter(val list: MutableList<SearchAvaliableFlightResponseData>)
    : BaseQuickAdapter<SearchAvaliableFlightResponseData, BaseViewHolder>(R.layout.item_ticket_search_result,
        list) {
    override fun convert(helper: BaseViewHolder?, item: SearchAvaliableFlightResponseData?) {
        helper?.let { holder ->
            item?.let { item ->
                //选出经济舱的价格
                val ticket = if(item.tickets[0].level == 1)
                    item.tickets[0]
                else
                    item.tickets[1]
                holder.setText(R.id.tvTurnOffTime, item.departTime)
                        .setText(R.id.tvArriveTime, item.arrivalTime)
                        .setText(R.id.tvFromAirport, "${item.departAirport.name}${item.fromTerminal}")
                        .setText(R.id.tvToAirport, "${item.arrivalAirport.name}${item.toTerminal}")
                        .setText(R.id.tvDuration, item.duration)
                        .setText(R.id.tvFlightNumberAndAccount,
                                mContext.getString(R.string.aircraft_number_and_account,
                                        item.aircraft.flightNumber, (ticket.discount * 10).toInt().toString()))
                        .setText(R.id.tvPrice,
                                mContext.getString(R.string.price,
                                        (ticket.price * ticket.discount).toInt().toString()))

            }
        }
    }
}