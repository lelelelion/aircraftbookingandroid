package cn.miaole.aircraft_booking_android.activitys.passengers

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.BaseRecyclerViewActivity
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_base_recycler_view.*
import kotlinx.android.synthetic.main.bar_item.*

class PassengersActivity : BaseRecyclerViewActivity<PassengersActivityPresenter>(),
        PassengersActivityContract.View {
    override fun loadPassengersSuccess(t: List<Passenger>, isRefresh: Boolean) {
        (adapter as PassengersAdapter)
                .apply {
                    if (isRefresh)
                        data.clear()
                    addData(t)
                }
    }

    override fun loadData(isRefresh: Boolean) {
        page = if (isRefresh)
            1
        else
            page + 1
        mPresenter.loadPassengers(page, isRefresh)
    }

    override fun onCretePresenter(): PassengersActivityPresenter =
            PassengersActivityPresenter(this)

    override fun initView() {
        super.initView()
        easyBar.init(mode = EasyBar.Mode.ICON_, titleRes = R.string.passenger_info, leftCallback = {
            onBackPressed()
        })

        adapter = PassengersAdapter(mutableListOf())
        adapter?.apply {
            bindToRecyclerView(recyclerView)
        }

        //不允许上拉加载更多
        refreshLayout.setLoadAble(false)

        //加载数据
        loadData(true)
    }
}
