package cn.miaole.aircraft_booking_android.activitys.edit_person_info

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView

interface EditPersonInfoActivityContract {
    interface View : BaseView {
        fun updateUserInfoSuccess()
    }

    interface Model : BaseModel {
        fun updateUserInfo(phone: String, nickname: String, email: String, gender: Int,
                           birthday: Long)
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun updateUserInfo(phone: String, nickname: String, email: String, gender: Int,
                                    birthday: Long)
        abstract fun updateUserInfoSuccess()
    }
}