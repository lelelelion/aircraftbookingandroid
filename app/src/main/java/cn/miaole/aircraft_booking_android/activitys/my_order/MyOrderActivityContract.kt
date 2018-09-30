package cn.miaole.aircraft_booking_android.activitys.my_order

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView
import cn.miaole.aircraft_booking_android.model.internet.data.GetOrdersResponseData

interface MyOrderActivityContract {
    interface View : BaseView {
        fun getOrdersSuccess(data: List<GetOrdersResponseData>, isRefresh: Boolean)
    }

    interface Model : BaseModel {
        fun getOrders(page: Int, size: Int, isRefresh: Boolean)
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun getOrdersSuccess(data: List<GetOrdersResponseData>, isRefresh: Boolean)
        abstract fun getOrders(page: Int, size: Int, isRefresh: Boolean)
    }
}