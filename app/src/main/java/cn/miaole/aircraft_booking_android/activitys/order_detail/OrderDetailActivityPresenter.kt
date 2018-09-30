package cn.miaole.aircraft_booking_android.activitys.order_detail

class OrderDetailActivityPresenter(orderDetailActivity: OrderDetailActivity)
    : OrderDetailActivityContract.Presenter() {
    init {
        mView = orderDetailActivity
        mModel = OrderDetailActivityModel(this)
    }
}