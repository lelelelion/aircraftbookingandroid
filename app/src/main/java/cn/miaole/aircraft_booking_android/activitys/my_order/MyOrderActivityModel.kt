package cn.miaole.aircraft_booking_android.activitys.my_order

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.EmptyResponseData
import cn.miaole.aircraft_booking_android.model.internet.data.Order
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper

class MyOrderActivityModel(val mPresenter: MyOrderActivityPresenter) : MyOrderActivityContract.Model {
    override fun deleteOrder(order: Order) {
        APIManager
                .deleteOrder(orderId = order.id)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<EmptyResponseData>(mPresenter) {
                    override fun _onNext(t: EmptyResponseData) {
                        mPresenter.deleteOrderSuccess(order)
                    }
                })
    }

    override fun getOrders(page: Int, size: Int, isRefresh: Boolean) {
        APIManager
                .getOrders(page, size)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<List<Order>>(mPresenter) {
                    override fun _onNext(t: List<Order>) {
                        mPresenter.getOrdersSuccess(t, isRefresh)
                    }
                })
    }
}