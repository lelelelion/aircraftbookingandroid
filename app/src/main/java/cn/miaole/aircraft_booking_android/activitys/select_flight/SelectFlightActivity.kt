package cn.miaole.aircraft_booking_android.activitys.select_flight

import android.annotation.SuppressLint
import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.activitys.booking.BookingActivity
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import cn.miaole.aircraft_booking_android.extensions.snackLoginTip
import cn.miaole.aircraft_booking_android.model.ABAApi
import cn.miaole.aircraft_booking_android.model.internet.data.SearchAvaliableFlightResponseData
import cn.miaole.aircraft_booking_android.model.params.IntentParam
import cn.miaole.aircraft_booking_android.utils.DateUtil
import cn.miaole.aircraft_booking_android.utils.easyToJson
import cn.miaole.aircraft_booking_android.utils.easyToObj
import cn.miaole.aircraft_booking_android.views.easy_refresh.CustomLinerLayoutManager
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_select_flight.*
import kotlinx.android.synthetic.main.bar_item.*
import org.jetbrains.anko.design.snackbar
import java.util.*

class SelectFlightActivity : MVPBaseActivity<SelectFlightActivityPresenter>(),
        SelectFlightActivityContract.View {

    companion object {
        const val PARAM_FLIGHT_INFO = "PARAM_FLIGHT_INFO"
    }

    override fun onCretePresenter(): SelectFlightActivityPresenter =
            SelectFlightActivityPresenter(this)


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_select_flight)

        initView()
    }

    private lateinit var flightInfo: SearchAvaliableFlightResponseData
    private lateinit var economyCabinAdapter: TicketAdapter
    private lateinit var businessCabinAdapter: TicketAdapter

    @SuppressLint("SetTextI18n")
    private fun initView() {
        easyBar.init(mode = EasyBar.Mode.ICON_, titleRes = R.string.select_cabin, leftCallback = {
            onBackPressed()
        })

        intent.apply {
            flightInfo = getStringExtra(PARAM_FLIGHT_INFO)
                    .easyToObj(SearchAvaliableFlightResponseData::class.java)
            val instance = Calendar.getInstance()
            instance.timeInMillis = flightInfo.tickets[0].effectDate
            tvFlightNumberAndDateAndWeek.text = getString(R.string.aircraft_number_and_date_and_week,
                    flightInfo.aircraft.flightNumber, "${instance.get(Calendar.YEAR)}" +
                    "-${instance.get(Calendar.MONTH) + 1}" +
                    "-${instance.get(Calendar.DAY_OF_MONTH)}",
                    DateUtil.getChineseWeek(instance.get(Calendar.DAY_OF_WEEK)))
            tvTurnOffTime.text = flightInfo.departTime
            tvArriveTime.text = flightInfo.arrivalTime
            tvFromAirport.text = "${flightInfo.departAirport.name}${flightInfo.fromTerminal}"
            tvToAirport.text = "${flightInfo.arrivalAirport.name}${flightInfo.toTerminal}"
            tvDuration.text = flightInfo.duration
        }

        economyCabinAdapter = TicketAdapter(mutableListOf())
        economyCabinAdapter.bindToRecyclerView(recyclerViewEconomyCabin)
        recyclerViewEconomyCabin.layoutManager = CustomLinerLayoutManager(this)
        economyCabinAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.btnBooking -> {
                    if (ABAApi.isLogin) {
                        (adapter as TicketAdapter).getItem(position)
                                ?.let {
                                    jumpTo(BookingActivity::class.java, IntentParam()
                                            .add(BookingActivity.PARAM_FLIGHT_INFO, flightInfo.easyToJson())
                                            .add(BookingActivity.PARAM_TICKET_INFO, it.easyToJson())
                                    )
                                }
                    } else {
                        this.snackLoginTip(easyBar)
                    }

                }
            }
        }

        businessCabinAdapter = TicketAdapter(mutableListOf())
        businessCabinAdapter.bindToRecyclerView(recyclerViewBusinessCabin)
        recyclerViewBusinessCabin.layoutManager = CustomLinerLayoutManager(this)
        businessCabinAdapter.setOnItemChildClickListener { adapter, view, position ->
            when (view.id) {
                R.id.btnBooking -> {
                    if (ABAApi.isLogin) {
                        (adapter as TicketAdapter).getItem(position)
                                ?.let {
                                    jumpTo(BookingActivity::class.java, IntentParam()
                                            .add(BookingActivity.PARAM_FLIGHT_INFO, flightInfo.easyToJson())
                                            .add(BookingActivity.PARAM_TICKET_INFO, it.easyToJson())
                                    )
                                }
                    } else {
                        this.snackLoginTip(easyBar)
                    }
                }
            }
        }

        flightInfo.tickets.forEach {
            when (it.level) {
                1 -> economyCabinAdapter.addData(it)
                0 -> businessCabinAdapter.addData(it)
            }
        }
    }


}
