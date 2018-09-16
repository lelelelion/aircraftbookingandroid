package cn.miaole.aircraft_booking_android.activitys.forget_pwd

class ForgetPwdActivityPresenter(forgetPwdActivity: ForgetPwdActivity)
    : ForgetPwdActivityContract.Presenter() {
    init {
        mView = forgetPwdActivity
        mModel = ForgetPwdActivityModel(this)
    }
}