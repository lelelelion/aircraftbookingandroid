package cn.miaole.aircraft_booking_android.activitys.register

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.extensions.toast

class RegisterActivityPresenter(val registerActivity: RegisterActivity)
    : RegisterActivityContract.Presenter() {
    override fun registerFail(msg: String) {
        mView.registerFail(msg)
    }

    override fun register(phone: String, password: String, rePassword: String) {
        if (phone == "" || password == "" || rePassword == "")
            registerActivity.toast(R.string.please_complete_info)
        else if (password != rePassword)
            registerActivity.toast(R.string.twice_password_not_same)
        else
            mModel.register(phone, password)
    }

    override fun registerSuccess(phone: String, password: String) {
        mView.registerSuccess(phone, password)
    }

    init {
        mView = registerActivity
        mModel = RegisterActivityMode(this)
    }
}