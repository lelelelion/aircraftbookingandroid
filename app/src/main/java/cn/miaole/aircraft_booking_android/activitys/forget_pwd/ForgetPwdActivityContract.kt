package cn.miaole.aircraft_booking_android.activitys.forget_pwd

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView

interface ForgetPwdActivityContract {
    interface View : BaseView {
        fun sendCodeSuccess()
        fun sendCodeFail(msg: String)
        fun generateNewPasswordSuccess(phone: String)
    }

    interface Model : BaseModel {
        fun sendVerificationCode(country: String, phone: String)
        fun forgetPassword(phone: String, newPassword: String, code: String, zone: String = "86")
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun sendVerificationCode(country: String, phone: String)
        abstract fun sendCodeSuccess()
        abstract fun sendCodeFail(msg: String)
        abstract fun forgetPassword(phone: String, newPassword: String, code: String, zone: String = "86")
        abstract fun generateNewPasswordSuccess(phone: String)
    }
}