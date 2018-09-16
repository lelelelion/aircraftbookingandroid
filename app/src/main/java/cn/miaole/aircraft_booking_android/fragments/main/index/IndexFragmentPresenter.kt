package cn.miaole.aircraft_booking_android.fragments.main.index

class IndexFragmentPresenter(indexFragment: IndexFragment)
    : IndexFragmentContract.Presenter() {
    init {
        mView = indexFragment
        mModel = IndexFragmentModel(this)
    }
}