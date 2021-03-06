package cn.miaole.aircraft_booking_android.activitys.add_passenger

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper
import com.orhanobut.logger.Logger

class AddPassengerActivityModel(val mPresenter: AddPassengerActivityPresenter)
    : AddPassengerActivityContact.Model {
    override fun updatePassengerContact(id: String, name: String, certificateType: Int, certificateValue: String,
                                        isAdult: Boolean, phone: String, email: String) {
        APIManager
                .updatePassengerContact(id, name, certificateType, certificateValue, isAdult, phone, email)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<Passenger>(mPresenter) {
                    override fun _onNext(t: Passenger) {
                        mPresenter.updatePassengerSuccess(t)
                    }
                })
    }

    override fun addPassengerContact(name: String, certificateType: Int, certificateValue: String,
                                     isAdult: Boolean, phone: String, email: String) {
        APIManager
                .addPassengerContact(name, certificateType, certificateValue, isAdult, phone, email)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<Passenger>(mPresenter) {
                    override fun _onNext(t: Passenger) {
                        mPresenter.addPassengerSuccess(passenger = t)
                    }
                })
    }
}