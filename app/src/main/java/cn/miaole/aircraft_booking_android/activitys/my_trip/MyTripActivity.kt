package cn.miaole.aircraft_booking_android.activitys.my_trip

import cn.miaole.aircraft_booking_android.activitys.base.activity.BaseRecyclerViewActivity

class MyTripActivity : BaseRecyclerViewActivity<MyTripActivityPresenter>(),
        MyTripActivityContract.View{
    override fun loadData(isRefresh: Boolean) {
    }

    override fun onCretePresenter(): MyTripActivityPresenter =
            MyTripActivityPresenter(this)

}
