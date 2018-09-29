package cn.miaole.aircraft_booking_android.activitys.add_passenger

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger

interface AddPassengerActivityContact {
    interface View : BaseView {
        fun addPassengerSuccess(passenger: Passenger)
        fun updatePassengerSuccess(passenger: Passenger)
    }

    interface Model : BaseModel {
        fun addPassengerContact(
                name: String, certificateType: Int, certificateValue: String, isAdult: Boolean,
                phone: String = "", email: String = ""
        )

        fun updatePassengerContact(
                id: String,
                name: String, certificateType: Int, certificateValue: String, isAdult: Boolean,
                phone: String = "", email: String = ""
        )
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun addPassengerContact(
                name: String, certificateType: Int, certificateValue: String, isAdult: Boolean,
                phone: String = "", email: String = ""
        )

        abstract fun updatePassengerContact(
                id: String,
                name: String, certificateType: Int, certificateValue: String, isAdult: Boolean,
                phone: String = "", email: String = ""
        )

        abstract fun addPassengerSuccess(passenger: Passenger)
        abstract fun updatePassengerSuccess(passenger: Passenger)

    }
}