package cn.miaole.aircraft_booking_android.activitys.login

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.LoginResponseData
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper
import cn.miaole.aircraft_booking_android.utils.easyToJson

class LoginActivityModel(mPresenter: LoginActivityPresenter)
    : LoginActivityContract.Model {
    override fun login(username: String, password: String) {
        APIManager
                .login(username, password)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<LoginResponseData>() {
                    override fun _onNext(t: LoginResponseData) {
                        println(t.easyToJson())
                    }
                })
    }
}