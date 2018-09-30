package cn.miaole.aircraft_booking_android.activitys.my_order

import cn.miaole.aircraft_booking_android.model.internet.data.Order

class MyOrderActivityPresenter(myOrderActivity: MyOrderActivity)
    : MyOrderActivityContract.Presenter() {
    override fun deleteOrder(order: Order) {
        mModel.deleteOrder(order)
    }

    override fun deleteOrderSuccess(order: Order) {
        mView.deleteOrderSuccess(order)
    }

    override fun getOrders(page: Int, size: Int, isRefresh: Boolean) {
        mModel.getOrders(page, size, isRefresh)
    }

    override fun getOrdersSuccess(data: List<Order>, isRefresh: Boolean) {
        mView.getOrdersSuccess(data, isRefresh)
    }

    init {
        mView = myOrderActivity
        mModel = MyOrderActivityModel(this)
    }
}