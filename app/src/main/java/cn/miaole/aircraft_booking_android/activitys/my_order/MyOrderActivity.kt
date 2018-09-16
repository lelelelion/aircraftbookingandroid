package cn.miaole.aircraft_booking_android.activitys.my_order

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.BaseRecyclerViewActivity
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*

class MyOrderActivity : BaseRecyclerViewActivity<MyOrderActivityPresenter>(),
        MyOrderActivityContract.View {
    override fun loadData(isRefresh: Boolean) {

    }

    override fun onCretePresenter(): MyOrderActivityPresenter =
            MyOrderActivityPresenter(this)

    override fun initView() {
        super.initView()
        easyBar.init(mode = EasyBar.Mode.ICON_, titleRes = R.string.my_order, leftCallback = {
            onBackPressed()
        })
    }
}