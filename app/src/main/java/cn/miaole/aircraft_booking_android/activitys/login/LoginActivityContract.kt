package cn.miaole.aircraft_booking_android.activitys.login

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView
import cn.miaole.aircraft_booking_android.model.internet.data.LoginResponseData

interface LoginActivityContract{
    interface View : BaseView {
        fun loginSuccess(loginResponseData: LoginResponseData)
    }

    interface Model : BaseModel {
        fun login(username: String, password: String)
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun login(username: String, password: String)
        abstract fun loginSuccess(loginResponseData: LoginResponseData)
    }
}