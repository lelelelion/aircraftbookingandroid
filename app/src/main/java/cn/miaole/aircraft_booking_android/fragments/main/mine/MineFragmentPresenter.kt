package cn.miaole.aircraft_booking_android.fragments.main.mine

class MineFragmentPresenter(mineFragment: MineFragment)
    : MineFragmentContract.Presenter() {
    init {
        mView = mineFragment
        mModel = MineFragmentModel(this)
    }
}