package cn.miaole.aircraft_booking_android.activitys.my_order

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.BaseRecyclerViewActivity
import cn.miaole.aircraft_booking_android.model.internet.data.GetOrdersResponseData
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.bar_item.*

class MyOrderActivity : BaseRecyclerViewActivity<MyOrderActivityPresenter>(),
        MyOrderActivityContract.View {
    override fun getOrdersSuccess(data: List<GetOrdersResponseData>, isRefresh: Boolean) {
        (adapter as MyOrderAdapter).apply {
            if (isRefresh)
                this.data.clear()
            this.addData(data)
        }
    }

    override fun loadData(isRefresh: Boolean) {
        page = if (isRefresh)
            1
        else
            page + 1
        mPresenter.getOrders(page, 10, isRefresh)
    }

    override fun onCretePresenter(): MyOrderActivityPresenter =
            MyOrderActivityPresenter(this)

    override fun initView() {
        super.initView()
        easyBar.init(mode = EasyBar.Mode.ICON_, titleRes = R.string.my_order, leftCallback = {
            onBackPressed()
        })

        adapter = MyOrderAdapter(mutableListOf())
        adapter?.apply {
            bindToRecyclerView(recyclerView)
        }
        loadData(true)
    }
}
