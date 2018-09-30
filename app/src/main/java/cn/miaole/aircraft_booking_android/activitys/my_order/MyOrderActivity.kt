package cn.miaole.aircraft_booking_android.activitys.my_order

import android.content.Intent
import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.BaseRecyclerViewActivity
import cn.miaole.aircraft_booking_android.activitys.order_detail.OrderDetailActivity
import cn.miaole.aircraft_booking_android.extensions.jumpForResult
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import cn.miaole.aircraft_booking_android.model.internet.data.GetOrdersResponseData
import cn.miaole.aircraft_booking_android.model.params.IntentParam
import cn.miaole.aircraft_booking_android.utils.easyToJson
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.bar_item.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.noButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MyOrderActivity : BaseRecyclerViewActivity<MyOrderActivityPresenter>(),
        MyOrderActivityContract.View {
    override fun deleteOrderSuccess(order: GetOrdersResponseData) {
        (adapter as MyOrderAdapter).apply {
            remove(data.indexOf(order))
        }
    }

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

    private var targetPosition = -1
    override fun initView() {
        super.initView()
        easyBar.init(mode = EasyBar.Mode.ICON_, titleRes = R.string.my_order, leftCallback = {
            onBackPressed()
        })

        adapter = MyOrderAdapter(mutableListOf())
        adapter?.apply {
            bindToRecyclerView(recyclerView)

            setOnItemClickListener { adapter, view, position ->
                (adapter as MyOrderAdapter).getItem(position)?.let { item ->
                    targetPosition = position
                    jumpForResult(OrderDetailActivity::class.java, 0, IntentParam()
                            .add(OrderDetailActivity.PARAM_GET_ORDERS_RESPONSE_DATA, item.easyToJson())
                    )
                }
            }
            setOnItemChildClickListener { adapter, view, position ->
                when (view.id) {
                    R.id.tvDeleteOrder -> {
                        (adapter as MyOrderAdapter).getItem(position)?.let { item ->
                            alert("", getString(R.string.confirm_delete)) {
                                yesButton { mPresenter.deleteOrder(item) }
                                noButton { }
                            }.show()
                        }
                    }
                }
            }
        }
        loadData(true)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            0 -> {
                data?.apply {
                    val isReturn = getBooleanExtra(OrderDetailActivity.RESULT_IS_RETURN, false)
//                    if (isReturn && targetPosition >= 0)
//                        adapter?.remove(targetPosition)
                }
            }
        }
    }
}
