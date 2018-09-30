package cn.miaole.aircraft_booking_android.activitys.login

import cn.miaole.aircraft_booking_android.model.ABAApi
import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.LoginResponseData
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper
import cn.miaole.aircraft_booking_android.utils.easyToJson

class LoginActivityModel(val mPresenter: LoginActivityPresenter)
    : LoginActivityContract.Model {


    override fun login(username: String, password: String) {
        APIManager
                .login(username, password)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<LoginResponseData>(mPresenter) {
                    override fun _onNext(t: LoginResponseData) {
                        //更新token
                        ABAApi.updateAuthorizationToken(t.token)
                        ABAApi.updateIsLogin(true)
                        ABAApi.updateUserInfo(t.user)

                        //登陆成功回调
                        mPresenter.loginSuccess(t)
                    }
                })
    }
}