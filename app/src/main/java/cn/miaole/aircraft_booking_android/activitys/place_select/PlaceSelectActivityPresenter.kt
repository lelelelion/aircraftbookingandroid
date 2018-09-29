package cn.miaole.aircraft_booking_android.activitys.place_select

import cn.miaole.aircraft_booking_android.model.internet.data.City

class PlaceSelectActivityPresenter(placeSelectActivity: PlaceSelectActivity)
    : PlaceSelectActivityContract.Presenter() {
    override fun getCitiesSuccess(list: List<City>) {
        mView.getCitiesSuccess(list)
    }

    override fun getCities() {
        mModel.getCitiesFromNetWork()
    }

    init {
        mView = placeSelectActivity
        mModel=  PlaceSelectActivityModel(this)
    }
}