package cn.miaole.aircraft_booking_android.activitys.edit_person_info

class EditPersonInfoActivityPresenter(editPersonInfoActivity: EditPersonInfoActivity)
    : EditPersonInfoActivityContract.Presenter() {
    override fun updateUserInfo(phone: String, nickname: String, email: String, gender: Int, birthday: Long) {
        mModel.updateUserInfo(phone, nickname, email, gender, birthday)
    }

    override fun updateUserInfoSuccess() {
        mView.updateUserInfoSuccess()
    }

    init {
        mView = editPersonInfoActivity
        mModel = EditPersonInfoActivityModel(this)
    }
}