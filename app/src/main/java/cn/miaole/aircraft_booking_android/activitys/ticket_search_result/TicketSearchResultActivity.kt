package cn.miaole.aircraft_booking_android.activitys.ticket_search_result

import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.BaseRecyclerViewActivity
import cn.miaole.aircraft_booking_android.model.internet.data.City
import cn.miaole.aircraft_booking_android.model.internet.data.SearchAvaliableFlightResponseData
import cn.miaole.aircraft_booking_android.utils.easyToObj
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_ticket_search_result.*
import kotlinx.android.synthetic.main.bar_item.*
import java.util.*

class TicketSearchResultActivity : BaseRecyclerViewActivity<TicketSearchResultActivityPresenter>(),
        TicketSearchResultActivityContact.View {
    companion object {
        const val PARAM_START_CITY = "PARAM_START_CITY"
        const val PARAM_TARGET_CITY = "PARAM_TARGET_CITY"
        const val PARAM_TURN_OFF_DATE = "PARAM_TURN_OFF_DATE"
    }

    override fun searchAvailableFlightSuccess(result: List<SearchAvaliableFlightResponseData>,
                                              isRefresh: Boolean) {
        (adapter as TicketSearchResultAdapter).apply {
            if(isRefresh)
                data.clear()
            addData(result)
        }
    }

    override fun loadData(isRefresh: Boolean) {
        page = if (isRefresh)
            1
        else
            page + 1
        mPresenter.searchAvailableFlight(fromCity.code, toCity.code, date, page, isRefresh = isRefresh)
    }

    override fun getRes(): Int = R.layout.activity_ticket_search_result

    override fun onCretePresenter(): TicketSearchResultActivityPresenter =
            TicketSearchResultActivityPresenter(this)

    private lateinit var fromCity: City
    private lateinit var toCity: City
    private var date: Long = 0L
    private var delta: Int = 0
    override fun initView() {
        super.initView()
        intent.apply {
            fromCity = getStringExtra(PARAM_START_CITY).easyToObj(City::class.java)
            toCity = getStringExtra(PARAM_TARGET_CITY).easyToObj(City::class.java)
            date = getLongExtra(PARAM_TURN_OFF_DATE, 0L)
        }

        setDate(date, delta)
        easyBar.init(mode = EasyBar.Mode.ICON_, title = "${fromCity.name}-${toCity.name}", leftCallback = {
            onBackPressed()
        })

        llPreDay.setOnClickListener {
            setDate(date, -1)
            loadData(true)
        }
        llNextDay.setOnClickListener {
            setDate(date, 1)
            loadData(true)
        }

        adapter = TicketSearchResultAdapter(mutableListOf())
        adapter?.apply {
            bindToRecyclerView(recyclerView)
        }
        //加载数据
        loadData(true)
    }


    private fun setDate(time: Long, delta: Int) {
        Calendar.getInstance()
                .apply {
                    timeInMillis = time
                    add(Calendar.DAY_OF_MONTH, delta)
                    date = timeInMillis
                    tvDate.text = getString(R.string.search_flight_result_date, this[Calendar.MONTH] + 1
                            , this[Calendar.DAY_OF_MONTH], getChineseWeek(this[Calendar.DAY_OF_WEEK]))
                }
    }

    private fun getChineseWeek(week: Int): String {
        return when (week) {
            1 -> "日"
            2 -> "一"
            3 -> "二"
            4 -> "三"
            5 -> "四"
            6 -> "五"
            7 -> "六"
            else -> {
                "一"
            }
        }
    }
}
