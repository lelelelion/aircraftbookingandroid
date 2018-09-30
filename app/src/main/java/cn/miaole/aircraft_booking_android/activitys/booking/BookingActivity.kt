package cn.miaole.aircraft_booking_android.activitys.booking

import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.activitys.main.MainActivity
import cn.miaole.aircraft_booking_android.activitys.select_flight.SelectFlightActivity
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import cn.miaole.aircraft_booking_android.extensions.toFixed
import cn.miaole.aircraft_booking_android.extensions.toast
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger
import cn.miaole.aircraft_booking_android.model.internet.data.SearchAvaliableFlightResponseData
import cn.miaole.aircraft_booking_android.model.internet.data.Ticket
import cn.miaole.aircraft_booking_android.utils.AccountValidatorUtil
import cn.miaole.aircraft_booking_android.utils.easyToJson
import cn.miaole.aircraft_booking_android.utils.easyToObj
import cn.miaole.aircraft_booking_android.views.easy_refresh.CustomLinerLayoutManager
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_booking.*
import kotlinx.android.synthetic.main.bar_item.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.noButton
import org.jetbrains.anko.selector
import org.jetbrains.anko.yesButton
import java.util.*

class BookingActivity : MVPBaseActivity<BookingActivityPresenter>(),
        BookingActivityContract.View {
    override fun generateOrderFail() {
        toast(R.string.generate_order_fail)
    }

    override fun generateOrderSuccess() {
        toast(R.string.generate_order_success)
        jumpTo(MainActivity::class.java)
    }

    override fun loadPassengersSuccess(t: List<Passenger>) {
        val countries = t.map { return@map "${it.name}(${it.certificateValue})" }
        selector("选择乘机人", countries) { dialogInterface, i ->
            if (bookingPassengerAdapter.data.indexOf(t[i]) >= 0)
                easyBar.snackbar(R.string.do_not_add_again)
            else
                bookingPassengerAdapter.addData(0, t[i])
        }
    }

    companion object {
        const val PARAM_FLIGHT_INFO = "PARAM_FLIGHT_INFO"
        const val PARAM_TICKET_INFO = "PARAM_TICKET_INFO"
    }

    override fun onCretePresenter(): BookingActivityPresenter =
            BookingActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_booking)

        initView()
    }

    private lateinit var flightInfo: SearchAvaliableFlightResponseData
    private lateinit var ticketInfo: Ticket
    private lateinit var bookingPassengerAdapter: BookingPassengerAdapter

    private fun initView() {
        easyBar.init(EasyBar.Mode.ICON_, titleRes = R.string.fill_in_order, leftCallback = {
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
                    getChineseWeek(instance.get(Calendar.DAY_OF_WEEK)))
            tvTurnOffTime.text = flightInfo.departTime
            tvArriveTime.text = flightInfo.arrivalTime
            tvFromAirport.text = "${flightInfo.departAirport.name}${flightInfo.fromTerminal}"
            tvToAirport.text = "${flightInfo.arrivalAirport.name}${flightInfo.toTerminal}"
            tvDuration.text = flightInfo.duration

            ticketInfo = getStringExtra(PARAM_TICKET_INFO)
                    .easyToObj(Ticket::class.java)
            tvPrice.text = getString(R.string.price, (ticketInfo.price * ticketInfo.discount).toInt())
        }


        recyclerView.layoutManager = GridLayoutManager(this, 5)
        bookingPassengerAdapter = BookingPassengerAdapter(mutableListOf())
        bookingPassengerAdapter.bindToRecyclerView(recyclerView)
        bookingPassengerAdapter.addData(Passenger("添加", "", ""
                , 0, "", "", "", 0L, 0L, -1, "", true))

        bookingPassengerAdapter.setOnItemClickListener { adapter, view, position ->
            (adapter as BookingPassengerAdapter).getItem(position)?.let {
                if (it.version == -1) {       //添加按钮
                    mPresenter.loadPassengers()
                }
            }
        }

        bookingPassengerAdapter.setOnItemLongClickListener { adapter, view, position ->
            (adapter as BookingPassengerAdapter).getItem(position)?.let {
                if (it.version != -1) {       //添加按钮
                    alert("", getString(R.string.confirm_delete)) {
                        yesButton {
                            bookingPassengerAdapter.remove(position)
                        }
                        noButton {
                        }
                    }.show()
                }
            }
            return@setOnItemLongClickListener true
        }
        btnBooking.setOnClickListener {
            if (bookingPassengerAdapter.data.size <= 1) {
                btnBooking.snackbar(R.string.please_add_passenger)
                return@setOnClickListener
            }
            val phone = etContactPhone.text.toString()
            val email = etContactEmail.text.toString()
            val name = etContactName.text.toString()
            if (name == "" || phone == "") {
                btnBooking.snackbar(R.string.please_complete_info)
                return@setOnClickListener
            }
            if (!AccountValidatorUtil.isMobile(phone)) {
                btnBooking.snackbar(R.string.please_input_validate_phone_number)
                return@setOnClickListener
            }
            if (email != "" && !AccountValidatorUtil.isEmail(email)) {
                btnBooking.snackbar(R.string.please_input_validate_email)
                return@setOnClickListener
            }
            val passengers = arrayListOf<String>()
            bookingPassengerAdapter.data.forEachIndexed { index, passenger ->
                if (index != bookingPassengerAdapter.data.size - 1)
                    passengers.add(passenger.id)
            }
            Logger.i(passengers.easyToJson())
            mPresenter.generateOrder(ticketInfo.id, passengers, name, phone, email)
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
