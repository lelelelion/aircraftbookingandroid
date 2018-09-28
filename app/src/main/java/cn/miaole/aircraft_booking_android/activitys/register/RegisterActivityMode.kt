package cn.miaole.aircraft_booking_android.activitys.register

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.RegisterResponseData
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper

class RegisterActivityMode(val mPresenter: RegisterActivityPresenter) : RegisterActivityContract.Model {
    override fun register(username: String, password: String) {
        APIManager.register(username, password)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<RegisterResponseData>() {
                    override fun _onNext(t: RegisterResponseData) {
                        mPresenter.registerSuccess(username, password)
                    }

                    override fun _onError(msg: String) {
                        super._onError(msg)
                        mPresenter.registerFail(msg)
                    }
                })
    }
}