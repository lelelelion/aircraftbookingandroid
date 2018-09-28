package cn.miaole.aircraft_booking_android.fragments.main.mine

class MineFragmentPresenter(mineFragment: MineFragment)
    : MineFragmentContract.Presenter() {
    override fun logout() {
        mModel.logout()
    }

    override fun logoutSuccess() {
        mView.logoutSuccess()
    }

    init {
        mView = mineFragment
        mModel = MineFragmentModel(this)
    }
}