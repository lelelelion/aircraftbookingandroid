package cn.miaole.aircraft_booking_android.activitys.my_order

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView
import cn.miaole.aircraft_booking_android.model.internet.data.Order

interface MyOrderActivityContract {
    interface View : BaseView {
        fun getOrdersSuccess(data: List<Order>, isRefresh: Boolean)
        fun deleteOrderSuccess(order: Order)
    }

    interface Model : BaseModel {
        fun getOrders(page: Int, size: Int, isRefresh: Boolean)
        fun deleteOrder(order: Order)
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun getOrdersSuccess(data: List<Order>, isRefresh: Boolean)
        abstract fun getOrders(page: Int, size: Int, isRefresh: Boolean)
        abstract fun deleteOrder(order: Order)
        abstract fun deleteOrderSuccess(order: Order)
    }
}