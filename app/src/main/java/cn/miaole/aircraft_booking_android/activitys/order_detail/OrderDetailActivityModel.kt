package cn.miaole.aircraft_booking_android.activitys.order_detail

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.EmptyResponseData
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper

class OrderDetailActivityModel(val mPresenter: OrderDetailActivityPresenter)
    : OrderDetailActivityContract.Model {
    override fun returnTicket(orderId: String) {
        APIManager
                .returnTicket(orderId)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<EmptyResponseData>(mPresenter) {
                    override fun _onNext(t: EmptyResponseData) {
                        mPresenter.returnTicketSuccess()
                    }
                })
    }
}