package cn.miaole.aircraft_booking_android.activitys.mine_info

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView
import cn.miaole.aircraft_booking_android.model.internet.data.User

interface MineInfoActivityContract {
    interface View : BaseView {
        fun getUserInfoSuccess(result: User)
    }

    interface Model : BaseModel {
        fun getUserInfo()
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun getUserInfo()
        abstract fun getUserInfoSuccess(result: User)
    }
}