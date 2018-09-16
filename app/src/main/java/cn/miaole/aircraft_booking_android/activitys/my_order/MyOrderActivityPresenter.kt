package cn.miaole.aircraft_booking_android.activitys.my_order

class MyOrderActivityPresenter(myOrderActivity: MyOrderActivity)
    : MyOrderActivityContract.Presenter() {
    init {
        mView = myOrderActivity
        mModel = MyOrderActivityModel(this)
    }
}