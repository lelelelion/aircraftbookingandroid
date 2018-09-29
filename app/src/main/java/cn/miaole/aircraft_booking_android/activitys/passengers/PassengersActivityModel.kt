package cn.miaole.aircraft_booking_android.activitys.passengers

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.EmptyResponseData
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper

class PassengersActivityModel(val mPresenter: PassengersActivityPresenter)
    : PassengersActivityContract.Model {
    override fun deletePassenger(passenger: Passenger) {
        APIManager
                .deletePassengerContact(passenger.id)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<EmptyResponseData>() {
                    override fun _onNext(t: EmptyResponseData) {
                        mPresenter.deletePassengerSuccess(passenger)
                    }
                })
    }

    override fun loadPassengers(page: Int, isRefresh: Boolean) {
        APIManager
                .getPassengerContacts()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<List<Passenger>>() {
                    override fun _onNext(t: List<Passenger>) {
                        mPresenter.loadPassengersSuccess(t, isRefresh)
                    }
                })
    }
}