package cn.miaole.aircraft_booking_android.activitys.order_detail

class OrderDetailActivityPresenter(orderDetailActivity: OrderDetailActivity)
    : OrderDetailActivityContract.Presenter() {
    override fun returnTicket(orderId: String) {
        mModel.returnTicket(orderId)
    }

    override fun returnTicketSuccess() {
        mView.returnTicketSuccess()
    }

    init {
        mView = orderDetailActivity
        mModel = OrderDetailActivityModel(this)
    }
}