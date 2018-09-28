package cn.miaole.aircraft_booking_android.activitys.register

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.extensions.toast

class RegisterActivityPresenter(val registerActivity: RegisterActivity)
    : RegisterActivityContract.Presenter() {
    override fun registerFail(msg: String) {
        mView.registerFail(msg)
    }

    override fun register(username: String, password: String, rePassword: String) {
        if (username == "" || password == "" || rePassword == "")
            registerActivity.toast(R.string.please_complete_info)
        else if (password != rePassword)
            registerActivity.toast(R.string.twice_password_not_same)
        else
            mModel.register(username, password)
    }

    override fun registerSuccess(username: String, password: String) {
        mView.registerSuccess(username, password)
    }

    init {
        mView = registerActivity
        mModel = RegisterActivityMode(this)
    }
}