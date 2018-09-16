package cn.miaole.aircraft_booking_android.activitys.register

class RegisterActivityPresenter(registerActivity: RegisterActivity)
    : RegisterActivityContract.Presenter() {
    init {
        mView = registerActivity
        mModel = RegisterActivityMode(this)
    }
}