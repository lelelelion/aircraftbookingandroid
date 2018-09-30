package cn.miaole.aircraft_booking_android.activitys.booking

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.EmptyResponseData
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper

class BookingActivityModel(val mPresenter: BookingActivityPresenter)
    : BookingActivityContract.Model {
    override fun generateOrder(ticketId: String, passengers: ArrayList<String>, name: String,
                               phone: String, email: String) {
        APIManager
                .generateOrder(ticketId, passengers, name, phone, email)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<EmptyResponseData>() {
                    override fun _onNext(t: EmptyResponseData) {
                        mPresenter.generateOrderSuccess()
                    }

                    override fun _onError(msg: String) {
                        super._onError(msg)
                        mPresenter.generateOrderFail()
                    }
                })
    }

    override fun loadPassengers() {
        APIManager
                .getPassengerContacts()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<List<Passenger>>() {
                    override fun _onNext(t: List<Passenger>) {
                        mPresenter.loadPassengersSuccess(t)
                    }
                })
    }
}