package cn.miaole.aircraft_booking_android.activitys.my_order

import cn.miaole.aircraft_booking_android.model.internet.data.GetOrdersResponseData

class MyOrderActivityPresenter(myOrderActivity: MyOrderActivity)
    : MyOrderActivityContract.Presenter() {
    override fun getOrders(page: Int, size: Int, isRefresh: Boolean) {
        mModel.getOrders(page, size, isRefresh)
    }

    override fun getOrdersSuccess(data: List<GetOrdersResponseData>, isRefresh: Boolean) {
        mView.getOrdersSuccess(data, isRefresh)
    }

    init {
        mView = myOrderActivity
        mModel = MyOrderActivityModel(this)
    }
}