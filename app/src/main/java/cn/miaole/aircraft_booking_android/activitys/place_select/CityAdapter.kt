package cn.miaole.aircraft_booking_android.activitys.place_select

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.place_select.item.CityBarItem
import cn.miaole.aircraft_booking_android.activitys.place_select.item.CityItem
import com.chad.library.adapter.base.BaseMultiItemQuickAdapter
import com.chad.library.adapter.base.BaseViewHolder
import com.chad.library.adapter.base.entity.MultiItemEntity

class CityAdapter(val list: MutableList<MultiItemEntity>)
    : BaseMultiItemQuickAdapter<MultiItemEntity, BaseViewHolder>(list){

    companion object {
        const val ITEM_TYPE_CITY_BAR = 0
        const val ITEM_TYPE_CITY = 1
    }
    init {
        addItemType(ITEM_TYPE_CITY_BAR, R.layout.item_city_bar)
        addItemType(ITEM_TYPE_CITY, R.layout.item_city)
    }
    override fun convert(helper: BaseViewHolder?, item: MultiItemEntity?) {
        helper?.let { holder ->
            item?.let { item ->
                when(item.itemType){
                    ITEM_TYPE_CITY_BAR -> {
                        holder.setText(R.id.tvText, (item as CityBarItem).text)
                    }
                    ITEM_TYPE_CITY -> {
                        holder.setText(R.id.tvCity, (item as CityItem).city.name)
                    }
                    else -> {
                    }
                }
            }
        }
    }

}