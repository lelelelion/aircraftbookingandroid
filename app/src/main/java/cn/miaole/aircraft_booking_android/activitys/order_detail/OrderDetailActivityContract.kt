package cn.miaole.aircraft_booking_android.activitys.order_detail

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView

interface OrderDetailActivityContract {
    interface View : BaseView {

    }

    interface Model : BaseModel {

    }

    abstract class Presenter : BasePresenter<View, Model>() {

    }
}