package cn.miaole.aircraft_booking_android.activitys.my_trip

import cn.miaole.aircraft_booking_android.model.internet.data.Order

class MyTripActivityPresenter(myTripActivity: MyTripActivity)
    : MyTripActivityContract.Presenter() {
    override fun getTrips(page: Int, size: Int, isRefresh: Boolean) {
        mModel.getTrips(page, size, isRefresh)
    }

    override fun getTripsSuccess(result: List<Order>, isRefresh: Boolean) {
        mView.getTripsSuccess(result, isRefresh)
    }

    init {
        mView = myTripActivity
        mModel = MyTripActivityModel(this)
    }
}