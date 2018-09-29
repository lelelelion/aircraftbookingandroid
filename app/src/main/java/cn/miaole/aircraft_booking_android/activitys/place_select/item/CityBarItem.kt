package cn.miaole.aircraft_booking_android.activitys.place_select.item

import cn.miaole.aircraft_booking_android.activitys.place_select.CityAdapter
import com.chad.library.adapter.base.entity.MultiItemEntity

class CityBarItem(val text: String): MultiItemEntity{
    override fun getItemType(): Int =
            CityAdapter.ITEM_TYPE_CITY_BAR
}