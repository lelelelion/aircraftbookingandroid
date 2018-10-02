package cn.miaole.aircraft_booking_android.activitys.mine_info

import cn.miaole.aircraft_booking_android.model.ABAApi
import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.User
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper

class MineInfoActivityModel(val mPresenter: MineInfoActivityPresenter) : MineInfoActivityContract.Model {
    override fun getUserInfo() {
        APIManager
                .getUserInfo()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<User>(mPresenter) {
                    override fun _onNext(t: User) {
                        ABAApi.updateUserInfo(t)
                        mPresenter.getUserInfoSuccess(t)
                    }
                })
    }
}