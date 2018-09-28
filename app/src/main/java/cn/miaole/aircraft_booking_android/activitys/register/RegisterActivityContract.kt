package cn.miaole.aircraft_booking_android.activitys.register

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView

interface RegisterActivityContract {
    interface View : BaseView {
        fun registerSuccess(username: String, password: String)
        fun registerFail(msg: String)
    }

    interface Model : BaseModel {
        fun register(username: String, password: String)
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun register(username: String, password: String, rePassword: String)
        abstract fun registerSuccess(username: String, password: String)
        abstract fun registerFail(msg: String)
    }
}