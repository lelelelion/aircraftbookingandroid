package cn.miaole.aircraft_booking_android.activitys.place_select

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.City
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper

class PlaceSelectActivityModel(val mPresenter: PlaceSelectActivityPresenter)
    : PlaceSelectActivityContract.Model {
    override fun getCitiesFromLocal() {

    }

    override fun getCitiesFromNetWork() {
        APIManager
                .getCities()
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<List<City>>() {
                    override fun _onNext(t: List<City>) {
                        mPresenter.getCitiesSuccess(t)
                    }
                })
    }
}