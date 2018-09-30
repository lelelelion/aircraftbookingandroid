package cn.miaole.aircraft_booking_android.fragments.main.mine

import cn.miaole.aircraft_booking_android.model.ABAApi
import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.User
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper
import com.orhanobut.logger.Logger

class MineFragmentModel(val mPresenter: MineFragmentPresenter) : MineFragmentContract.Model {
    override fun getUserInfo() {
        APIManager.getUserInfo()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<User>(mPresenter){
                    override fun _onNext(t: User) {
                        ABAApi.updateUserInfo(t)
                        mPresenter.getUserInfoSuccess()
                    }
                })
    }

    override fun logout() {
        ABAApi.updateAuthorizationToken("")
        ABAApi.updateIsLogin(false)
        ABAApi.updateUserInfo(null)
        mPresenter.logoutSuccess()
    }
}