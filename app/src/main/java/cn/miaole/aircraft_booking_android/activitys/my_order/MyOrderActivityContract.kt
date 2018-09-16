package cn.miaole.aircraft_booking_android.activitys.my_order

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView

interface MyOrderActivityContract {
    interface View : BaseView {

    }

    interface Model : BaseModel {

    }

    abstract class Presenter : BasePresenter<View, Model>() {

    }
}