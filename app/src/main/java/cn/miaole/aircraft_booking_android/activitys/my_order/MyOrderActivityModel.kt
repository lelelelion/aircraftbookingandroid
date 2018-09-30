package cn.miaole.aircraft_booking_android.activitys.my_order

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.GetOrdersResponseData
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper

class MyOrderActivityModel(val mPresenter: MyOrderActivityPresenter) : MyOrderActivityContract.Model {
    override fun getOrders(page: Int, size: Int, isRefresh: Boolean) {
        APIManager
                .getOrders(page, size)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<List<GetOrdersResponseData>>() {
                    override fun _onNext(t: List<GetOrdersResponseData>) {
                        mPresenter.getOrdersSuccess(t, isRefresh)
                    }
                })
    }
}