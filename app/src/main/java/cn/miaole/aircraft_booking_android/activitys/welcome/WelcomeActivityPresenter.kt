package cn.miaole.aircraft_booking_android.activitys.welcome

class WelcomeActivityPresenter(welcomeActivity: WelcomeActivity)
    : WelcomeActivityContract.Presenter() {
    init {
        mView = welcomeActivity
        mModel = WelcomeActivityModel(this)
    }
}