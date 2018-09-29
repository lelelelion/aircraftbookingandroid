package cn.miaole.aircraft_booking_android.fragments.main.index

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.fragment.MVPBaseFragment
import cn.miaole.aircraft_booking_android.extensions.showSelectDateDialog
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*
import kotlinx.android.synthetic.main.fragment_main_index.*
import android.content.Intent
import cn.miaole.aircraft_booking_android.activitys.place_select.PlaceSelectActivity
import cn.miaole.aircraft_booking_android.activitys.ticket_search_result.TicketSearchResultActivity
import cn.miaole.aircraft_booking_android.extensions.jumpForResult
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import cn.miaole.aircraft_booking_android.model.internet.data.City
import cn.miaole.aircraft_booking_android.model.params.IntentParam
import cn.miaole.aircraft_booking_android.utils.easyToJson
import cn.miaole.aircraft_booking_android.utils.easyToObj
import org.jetbrains.anko.design.snackbar
import java.util.*


class IndexFragment : MVPBaseFragment<IndexFragmentPresenter>(), IndexFragmentContract.View {

    companion object {
        const val REQUEST_CODE_START_PLACE = 0
        const val REQUEST_CODE_TARGET_PLACE = 1
        fun newInstance(): IndexFragment {
            val args = Bundle()
            val fragment = IndexFragment()
            fragment.arguments = args
            return fragment
        }
    }

    private var startPlace: City? = null
    private var targetPlace: City? = null

    override fun onCreatePresenter(): IndexFragmentPresenter =
            IndexFragmentPresenter(this)

    override fun getRes(): Int = R.layout.fragment_main_index

    override fun initView() {
        easyBar.init(mode = EasyBar.Mode.NONE, titleRes = R.string.index)
        lmiSelectDate.setOnClickListener { view ->
            //选择出发日期
            activity?.showSelectDateDialog { v, year, month, day ->
                lmiSelectDate.setValue("$year-${month + 1}-$day")
            }
        }

        tvStartLocation.setOnClickListener {
            jumpForResult(PlaceSelectActivity::class.java, REQUEST_CODE_START_PLACE)
        }

        tvTargetLocation.setOnClickListener {
            jumpForResult(PlaceSelectActivity::class.java, REQUEST_CODE_TARGET_PLACE)
        }

        btnSearch.setOnClickListener {
            //搜索
            if(startPlace == null){
                btnSearch.snackbar(R.string.please_select_from_city)
                return@setOnClickListener
            }
            if(targetPlace == null) {
                btnSearch.snackbar(R.string.please_select_to_city)
                return@setOnClickListener
            }

            if(lmiSelectDate.tv_value?.text == getString(R.string.please_select)){
                btnSearch.snackbar(R.string.please_select_date)
                return@setOnClickListener
            }

            val cal = Calendar.getInstance()
            val dateArr = lmiSelectDate.tv_value!!.text.split("-")
            if(dateArr.size != 3)
                return@setOnClickListener
            cal.set(Calendar.YEAR, dateArr[0].toInt())
            cal.set(Calendar.MONTH, dateArr[1].toInt() - 1)
            cal.set(Calendar.DAY_OF_MONTH, dateArr[2].toInt())
            activity?.jumpTo(TicketSearchResultActivity::class.java, IntentParam()
                    .add(TicketSearchResultActivity.PARAM_START_CITY, startPlace!!.easyToJson())
                    .add(TicketSearchResultActivity.PARAM_TARGET_CITY, targetPlace!!.easyToJson())
                    .add(TicketSearchResultActivity.PARAM_TURN_OFF_DATE, cal.timeInMillis)
            )

        }

    }


    override fun initialLoadData() {
    }


    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            REQUEST_CODE_START_PLACE -> {       //选择起点
                data?.apply {
                    startPlace = getStringExtra(PlaceSelectActivity.RESULT_CITY)
                            .easyToObj(City::class.java)
                    tvStartLocation.text = startPlace?.name ?: ""
                }
            }
            REQUEST_CODE_TARGET_PLACE -> {
                data?.apply {
                    targetPlace = getStringExtra(PlaceSelectActivity.RESULT_CITY)
                            .easyToObj(City::class.java)
                    tvTargetLocation.text = targetPlace?.name ?: ""
                }
            }
        }
    }
}