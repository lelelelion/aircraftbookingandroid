package cn.miaole.aircraft_booking_android.activitys.passengers

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.BaseRecyclerViewActivity
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*

class PassengersActivity : BaseRecyclerViewActivity<PassengersActivityPresenter>(),
        PassengersActivityContract.View{
    override fun loadData(isRefresh: Boolean) {

    }

    override fun onCretePresenter(): PassengersActivityPresenter =
            PassengersActivityPresenter(this)

    override fun initView() {
        super.initView()
        easyBar.init(mode = EasyBar.Mode.ICON_, titleRes = R.string.passenger_info, leftCallback = {
            onBackPressed()
        })
    }
}
