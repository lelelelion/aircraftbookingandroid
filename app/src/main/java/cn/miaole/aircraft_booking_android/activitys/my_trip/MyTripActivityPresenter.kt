package cn.miaole.aircraft_booking_android.activitys.my_trip

class MyTripActivityPresenter(myTripActivity: MyTripActivity)
    : MyTripActivityContract.Presenter() {
    init {
        mView = myTripActivity
        mModel = MyTripActivityModel(this)
    }
}