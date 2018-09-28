package cn.miaole.aircraft_booking_android.activitys.passengers

import cn.miaole.aircraft_booking_android.model.internet.data.Passenger

class PassengersActivityPresenter(passengersActivity: PassengersActivity)
    : PassengersActivityContract.Presenter() {
    override fun loadPassengersSuccess(t: List<Passenger>, isRefresh: Boolean) {
        mView.loadPassengersSuccess(t, isRefresh)
    }

    override fun loadPassengers(page: Int, isRefresh: Boolean) {
        mModel.loadPassengers(page, isRefresh)
    }

    init {
        mView = passengersActivity
        mModel = PassengersActivityModel(this)
    }
}