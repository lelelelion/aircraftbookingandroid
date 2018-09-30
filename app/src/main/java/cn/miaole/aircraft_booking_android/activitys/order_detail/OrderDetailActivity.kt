package cn.miaole.aircraft_booking_android.activitys.order_detail

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.support.v7.widget.GridLayoutManager
import android.view.View
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.activitys.booking.BookingPassengerAdapter
import cn.miaole.aircraft_booking_android.extensions.toast
import cn.miaole.aircraft_booking_android.model.internet.data.Flight
import cn.miaole.aircraft_booking_android.model.internet.data.Order
import cn.miaole.aircraft_booking_android.utils.DateUtil
import cn.miaole.aircraft_booking_android.utils.easyToObj
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_order_detail.*
import kotlinx.android.synthetic.main.bar_item.*
import java.util.*

class OrderDetailActivity : MVPBaseActivity<OrderDetailActivityPresenter>(),
        OrderDetailActivityContract.View {

    companion object {
        const val PARAM_GET_ORDERS_RESPONSE_DATA = "PARAM_GET_ORDERS_RESPONSE_DATA"
        const val RESULT_IS_RETURN = "RESULT_IS_RETURN"
    }

    override fun returnTicketSuccess() {
        Intent().apply {
            putExtra(RESULT_IS_RETURN, true)
            setResult(0, this)
        }
        toast(R.string.return_ticket_success)
        finish()
    }

    override fun onCretePresenter(): OrderDetailActivityPresenter =
            OrderDetailActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_order_detail)

        initView()
    }

    private lateinit var getOrdersResponseData: Order
    private lateinit var flightInfo: Flight
    private lateinit var adapter: BookingPassengerAdapter

    @SuppressLint("SetTextI18n")
    private fun initView() {
        easyBar.init(mode = EasyBar.Mode.ICON_, titleRes = R.string.order_detail, leftCallback = {
            onBackPressed()
        })

        intent.apply {
            getOrdersResponseData = getStringExtra(PARAM_GET_ORDERS_RESPONSE_DATA)
                    .easyToObj(Order::class.java)
            flightInfo = getOrdersResponseData.Ticket.Flight
            val instance = Calendar.getInstance()
            instance.timeInMillis = getOrdersResponseData.Ticket.effectDate
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

        tvContact.text = "${getOrdersResponseData.contactName}(${getOrdersResponseData.phone})"
        tvTotalPrice.text = getString(R.string.total_price, getOrdersResponseData.cost.toString())

        passengersRecyclerView.layoutManager = GridLayoutManager(this, 5)
        adapter = BookingPassengerAdapter(mutableListOf())
        adapter.apply {
            bindToRecyclerView(passengersRecyclerView)
            adapter.addData(getOrdersResponseData.passengers)
        }

        if (getOrdersResponseData.status != 0) {      //订单已完成，不能退票
            btnReturnTicket.visibility = View.GONE
        } else {
            btnReturnTicket.setOnClickListener {
                mPresenter.returnTicket(getOrdersResponseData.id)
            }
        }

    }
}
