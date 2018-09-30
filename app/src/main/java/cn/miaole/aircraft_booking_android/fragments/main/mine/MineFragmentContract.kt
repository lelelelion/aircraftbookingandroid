package cn.miaole.aircraft_booking_android.fragments.main.mine

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView

interface MineFragmentContract {
    interface View : BaseView {
        fun logoutSuccess()
        fun getUserInfoSuccess()
    }

    interface Model : BaseModel {
        fun logout()
        fun getUserInfo()
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun logout()
        abstract fun logoutSuccess()
        abstract fun getUserInfoSuccess()
        abstract fun getUserInfo()
    }
}