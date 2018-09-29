package cn.miaole.aircraft_booking_android.activitys.select_flight

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.extensions.toFixed
import cn.miaole.aircraft_booking_android.model.internet.data.Ticket
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class TicketAdapter(list: MutableList<Ticket>)
    : BaseQuickAdapter<Ticket, BaseViewHolder>(R.layout.item_ticket, list) {
    override fun convert(helper: BaseViewHolder?, item: Ticket?) {
        helper?.let { holder ->
            item?.let { item ->
                helper.setText(R.id.tvCabinType, when (item.level) {
                    0 -> "商务舱"
                    1 -> "经济舱"
                    else -> ""
                })
                        .setText(R.id.tvDiscount,
                                mContext.getString(R.string.discount, (item.discount * 10).toFixed(1)))
                        .setText(R.id.tvTicketNum,
                                mContext.getString(R.string.remain_ticket_num, item.standbyTicket.toString()))
                        .setText(R.id.tvPrice,
                                mContext.getString(R.string.price, (item.price * item.discount).toFixed(1)))

            }
        }
    }
}