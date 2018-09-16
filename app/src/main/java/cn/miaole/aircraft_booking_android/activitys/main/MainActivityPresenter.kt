package cn.miaole.aircraft_booking_android.activitys.main

class MainActivityPresenter(mainActivity: MainActivity)
    : MainActivityContract.Presenter() {
    init {
        mView = mainActivity
        mModel = MainActivityModel(this)
    }
}