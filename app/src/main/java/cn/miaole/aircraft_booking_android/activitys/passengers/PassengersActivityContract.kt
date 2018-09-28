package cn.miaole.aircraft_booking_android.activitys.passengers

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger

interface PassengersActivityContract {
    interface View : BaseView {
        fun loadPassengersSuccess(t: List<Passenger>, isRefresh: Boolean)
    }

    interface Model : BaseModel {
        fun loadPassengers(page: Int, isRefresh: Boolean)
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun loadPassengers(page: Int, isRefresh: Boolean)
        abstract fun loadPassengersSuccess(t: List<Passenger>, isRefresh: Boolean)
    }
}