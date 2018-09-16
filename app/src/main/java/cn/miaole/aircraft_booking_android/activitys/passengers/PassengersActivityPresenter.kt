package cn.miaole.aircraft_booking_android.activitys.passengers

class PassengersActivityPresenter(passengersActivity: PassengersActivity)
    : PassengersActivityContract.Presenter() {
    init {
        mView = passengersActivity
        mModel = PassengersActivityModel(this)
    }
}