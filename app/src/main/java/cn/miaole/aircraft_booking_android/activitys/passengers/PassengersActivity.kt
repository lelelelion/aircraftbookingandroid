package cn.miaole.aircraft_booking_android.activitys.passengers

import android.animation.Animator
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.add_passenger.AddPassengerActivity
import cn.miaole.aircraft_booking_android.activitys.base.activity.BaseRecyclerViewActivity
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger
import cn.miaole.aircraft_booking_android.model.params.IntentParam
import cn.miaole.aircraft_booking_android.utils.easyToJson
import cn.miaole.aircraft_booking_android.views.arcmenu2.ArcMenu
import cn.miaole.aircraft_booking_android.views.arcmenu2.DensityUtil
import cn.miaole.aircraft_booking_android.views.arcmenu2.FloatingButton
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_base_recycler_view.*
import kotlinx.android.synthetic.main.bar_item.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.noButton
import org.jetbrains.anko.yesButton

class PassengersActivity : BaseRecyclerViewActivity<PassengersActivityPresenter>(),
        PassengersActivityContract.View {
    override fun deletePassengerSuccess(passenger: Passenger) {
        (adapter as PassengersAdapter)
                .apply {
                    remove(data.indexOf(passenger))
                }
    }


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

    override fun onResume() {
        super.onResume()
        loadData(true)
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
            setOnItemClickListener { adapter, view, position ->
                (adapter as PassengersAdapter).getItem(position)?.let {
                    jumpTo(AddPassengerActivity::class.java, IntentParam()
                            .add(AddPassengerActivity.PARAM_PASSENGER, it.easyToJson()))
                }
            }

            setOnItemLongClickListener { adapter, view, position ->
                alert(R.string.comfirm_delete) {
                    yesButton {
                        (adapter as PassengersAdapter).getItem(position)?.let {
                            mPresenter.deletePassenger(it)
                        }
                    }
                    noButton {  }
                }.show()

                return@setOnItemLongClickListener true
            }

        }

        val fab = FloatingButton(this, FloatingButton.Position.POSITION_BOTTOM_RIGHT,
                DensityUtil.dip2px(this, 50f), contentRes = R.drawable.icon_add,
                contentMargin = DensityUtil.dip2px(this, 12f),
                layoutMarginVertical = DensityUtil.dip2px(this, 24f),
                layoutMarginHorizon = DensityUtil.dip2px(this, 24f))

        fab.setOnClickListener {
            jumpTo(AddPassengerActivity::class.java)
        }
        //不允许上拉加载更多
        refreshLayout.setLoadAble(false)

    }
}
