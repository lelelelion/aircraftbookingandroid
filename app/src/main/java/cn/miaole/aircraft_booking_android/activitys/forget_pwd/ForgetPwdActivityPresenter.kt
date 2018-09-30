package cn.miaole.aircraft_booking_android.activitys.forget_pwd

class ForgetPwdActivityPresenter(forgetPwdActivity: ForgetPwdActivity)
    : ForgetPwdActivityContract.Presenter() {
    override fun forgetPassword(phone: String, newPassword: String, code: String, zone: String) {
        mModel.forgetPassword(phone, newPassword, code, zone)
    }

    override fun generateNewPasswordSuccess(phone: String) {
        mView.generateNewPasswordSuccess(phone)
    }

    override fun sendVerificationCode(country: String, phone: String) {
        mModel.sendVerificationCode(country, phone)
    }

    override fun sendCodeSuccess() {
        mView.sendCodeSuccess()
    }

    override fun sendCodeFail(msg: String) {
        mView.sendCodeFail(msg)
    }

    init {
        mView = forgetPwdActivity
        mModel = ForgetPwdActivityModel(this)
    }
}