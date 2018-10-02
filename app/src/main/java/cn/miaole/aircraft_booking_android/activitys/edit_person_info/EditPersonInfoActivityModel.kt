package cn.miaole.aircraft_booking_android.activitys.edit_person_info

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.EmptyResponseData
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper

class EditPersonInfoActivityModel(val mPresenter: EditPersonInfoActivityPresenter)
    : EditPersonInfoActivityContract.Model {
    override fun updateUserInfo(phone: String, nickname: String, email: String, gender: Int,
                                birthday: Long) {
        APIManager
                .updateUserInfo(phone, nickname, email, gender, birthday)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<EmptyResponseData>(mPresenter) {
                    override fun _onNext(t: EmptyResponseData) {
                        mPresenter.updateUserInfoSuccess()
                    }
                })
    }
}