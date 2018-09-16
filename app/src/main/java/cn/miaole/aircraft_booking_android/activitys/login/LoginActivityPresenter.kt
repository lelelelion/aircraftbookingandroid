package cn.miaole.aircraft_booking_android.activitys.login

class LoginActivityPresenter(loginActivity: LoginActivity)
    : LoginActivityContract.Presenter() {
    init {
        mView = loginActivity
        mModel = LoginActivityModel(this)
    }
}