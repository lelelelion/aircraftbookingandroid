package cn.miaole.aircraft_booking_android.activitys.mine_info

class MineInfoActivityPresenter(mineInfoActivity: MineInfoActivity)
    : MineInfoActivityContract.Presenter() {
    init {
        mView = mineInfoActivity
        mModel = MineInfoActivityModel(this)
    }
}