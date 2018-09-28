package cn.miaole.aircraft_booking_android.activitys.passengers

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger
import com.chad.library.adapter.base.BaseQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder

class PassengersAdapter(private val mList: MutableList<Passenger>)
    : BaseQuickAdapter<Passenger, BaseViewHolder>(R.layout.item_passengers, mList){
    override fun convert(helper: BaseViewHolder?, item: Passenger?) {
        helper?.let { holder->
            item?.let { item ->
                holder.setText(R.id.tvName, item.name)
                        .setText(R.id.tvID, item.certificateValue)
                        .setText(R.id.tvManType, if(item.isAdult) "成人" else "儿童")
            }
        }
    }

}