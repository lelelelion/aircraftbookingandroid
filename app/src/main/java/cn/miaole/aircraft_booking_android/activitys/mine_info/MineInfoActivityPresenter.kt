package cn.miaole.aircraft_booking_android.activitys.mine_info

import cn.miaole.aircraft_booking_android.model.internet.data.User

class MineInfoActivityPresenter(mineInfoActivity: MineInfoActivity)
    : MineInfoActivityContract.Presenter() {
    override fun getUserInfo() {
        mModel.getUserInfo()
    }

    override fun getUserInfoSuccess(result: User) {
        mView.getUserInfoSuccess(result)
    }

    init {
        mView = mineInfoActivity
        mModel = MineInfoActivityModel(this)
    }
}