package cn.miaole.aircraft_booking_android.activitys.my_trip

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.Order
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper

class MyTripActivityModel(val mPresenter: MyTripActivityPresenter) : MyTripActivityContract.Model {
    override fun getTrips(page: Int, size: Int, isRefresh: Boolean) {
        APIManager.getTrtips(page, size)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<List<Order>>(mPresenter){
                    override fun _onNext(t: List<Order>) {
                        mPresenter.getTripsSuccess(t, isRefresh)
                    }
                })
    }
}