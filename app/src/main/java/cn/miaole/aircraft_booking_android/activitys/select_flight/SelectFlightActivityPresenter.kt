package cn.miaole.aircraft_booking_android.activitys.select_flight

class SelectFlightActivityPresenter(selectFlightActivity: SelectFlightActivity)
    : SelectFlightActivityContract.Presenter() {
    init {
        mView = selectFlightActivity
        mModel = SelectFlightActivityModel(this)
    }
}