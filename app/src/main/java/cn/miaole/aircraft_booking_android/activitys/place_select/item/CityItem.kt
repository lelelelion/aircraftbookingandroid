package cn.miaole.aircraft_booking_android.activitys.place_select.item

import cn.miaole.aircraft_booking_android.activitys.place_select.CityAdapter
import cn.miaole.aircraft_booking_android.model.internet.data.City
import com.chad.library.adapter.base.entity.MultiItemEntity

class CityItem(val city: City): MultiItemEntity{
    override fun getItemType(): Int = CityAdapter.ITEM_TYPE_CITY

}