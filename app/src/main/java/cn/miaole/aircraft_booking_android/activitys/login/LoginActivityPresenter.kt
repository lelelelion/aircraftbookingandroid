package cn.miaole.aircraft_booking_android.activitys.login

import cn.miaole.aircraft_booking_android.model.internet.data.LoginResponseData

class LoginActivityPresenter(loginActivity: LoginActivity)
    : LoginActivityContract.Presenter() {

    override fun login(username: String, password: String) {
        mModel.login(username, password)
    }

    override fun loginSuccess(loginResponseData: LoginResponseData) {
        mView.loginSuccess(loginResponseData)
    }

    init {
        mView = loginActivity
        mModel = LoginActivityModel(this)
    }
}