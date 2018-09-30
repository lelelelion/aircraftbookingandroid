package cn.miaole.aircraft_booking_android.activitys.my_trip

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.BaseRecyclerViewActivity
import cn.miaole.aircraft_booking_android.model.internet.data.Order
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.bar_item.*

class MyTripActivity : BaseRecyclerViewActivity<MyTripActivityPresenter>(),
        MyTripActivityContract.View {
    override fun getTripsSuccess(result: List<Order>, isRefresh: Boolean) {
        (adapter as MyTripAdapter).apply {
            if (isRefresh)
                data.clear()
            addData(result)
        }
    }

    override fun loadData(isRefresh: Boolean) {
        page = if (isRefresh)
            1
        else
            page + 1
        mPresenter.getTrips(page, 10, isRefresh)
    }

    override fun onCretePresenter(): MyTripActivityPresenter =
            MyTripActivityPresenter(this)

    override fun initView() {
        super.initView()
        easyBar.init(mode = EasyBar.Mode.ICON_, titleRes = R.string.my_trip, leftCallback = {
            onBackPressed()
        })

        adapter = MyTripAdapter(mutableListOf())
        adapter?.apply {
            bindToRecyclerView(recyclerView)
        }

        loadData(true)
    }

}
