package cn.miaole.aircraft_booking_android.activitys.booking

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger

interface BookingActivityContract {
    interface View : BaseView {
        fun loadPassengersSuccess(t: List<Passenger>)
        fun generateOrderSuccess()
        fun generateOrderFail()
    }

    interface Model : BaseModel {
        fun loadPassengers()
        fun generateOrder(ticketId: String, passengers: ArrayList<String>, name: String,
                          phone: String, email: String = "")
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun loadPassengersSuccess(t: List<Passenger>)
        abstract fun loadPassengers()
        abstract fun generateOrder(ticketId: String, passengers: ArrayList<String>, name: String,
                                   phone: String, email: String = "")

        abstract fun generateOrderSuccess()
        abstract fun generateOrderFail()
    }
}