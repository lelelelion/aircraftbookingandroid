package cn.miaole.aircraft_booking_android.activitys.my_trip

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.BaseRecyclerViewActivity
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*

class MyTripActivity : BaseRecyclerViewActivity<MyTripActivityPresenter>(),
        MyTripActivityContract.View{
    override fun loadData(isRefresh: Boolean) {
    }

    override fun onCretePresenter(): MyTripActivityPresenter =
            MyTripActivityPresenter(this)

    override fun initView() {
        super.initView()
        easyBar.init(mode = EasyBar.Mode.ICON_, titleRes = R.string.my_trip, leftCallback = {
            onBackPressed()
        })
    }

}
