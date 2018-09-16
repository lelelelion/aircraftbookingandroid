package cn.miaole.aircraft_booking_android.fragments.main.treasure

class TreasureFragmentPresenter(treasureFragment: TreasureFragment)
    : TreasureFragmentContract.Presenter() {
    init {
        mView = treasureFragment
        mModel = TreasureFragmentModel(this)
    }
}