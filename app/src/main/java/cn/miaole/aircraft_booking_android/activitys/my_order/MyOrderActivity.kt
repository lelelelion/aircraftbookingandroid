package cn.miaole.aircraft_booking_android.activitys.my_order

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.BaseRecyclerViewActivity

class MyOrderActivity : BaseRecyclerViewActivity<MyOrderActivityPresenter>(),
        MyOrderActivityContract.View {
    override fun loadData(isRefresh: Boolean) {

    }

    override fun onCretePresenter(): MyOrderActivityPresenter =
            MyOrderActivityPresenter(this)

}
