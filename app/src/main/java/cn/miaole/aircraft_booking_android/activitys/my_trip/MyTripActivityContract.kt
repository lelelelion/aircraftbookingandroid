package cn.miaole.aircraft_booking_android.activitys.my_trip

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView
import cn.miaole.aircraft_booking_android.model.internet.data.Order

interface MyTripActivityContract {
    interface View : BaseView {
        fun getTripsSuccess(result: List<Order>, isRefresh: Boolean)
    }

    interface Model : BaseModel {
        fun getTrips(page: Int, size: Int, isRefresh: Boolean)
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun getTrips(page: Int, size: Int, isRefresh: Boolean)
        abstract fun getTripsSuccess(result: List<Order>, isRefresh: Boolean)
    }
}