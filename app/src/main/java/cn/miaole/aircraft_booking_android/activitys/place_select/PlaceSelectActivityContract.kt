package cn.miaole.aircraft_booking_android.activitys.place_select

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView
import cn.miaole.aircraft_booking_android.model.internet.data.City

interface PlaceSelectActivityContract {
    interface View : BaseView {
        fun getCitiesSuccess(list: List<City>)
    }

    interface Model : BaseModel {
        fun getCitiesFromNetWork()
        fun getCitiesFromLocal()
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun getCities()
        abstract fun getCitiesSuccess(list: List<City>)
    }
}