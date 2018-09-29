package cn.miaole.aircraft_booking_android.fragments.main.index

import android.app.DatePickerDialog
import android.content.Context
import android.os.Bundle
import android.widget.DatePicker
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.fragment.MVPBaseFragment
import cn.miaole.aircraft_booking_android.extensions.showSelectDateDialog
import cn.miaole.aircraft_booking_android.extensions.toast
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import cn.smssdk.gui.RegisterPage
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*
import kotlinx.android.synthetic.main.fragment_main_index.*
import android.R.attr.data
import android.content.Intent
import cn.miaole.aircraft_booking_android.activitys.place_select.PlaceSelectActivity
import cn.miaole.aircraft_booking_android.extensions.jumpForResult
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import cn.miaole.aircraft_booking_android.model.internet.data.City
import cn.miaole.aircraft_booking_android.utils.easyToJson
import cn.miaole.aircraft_booking_android.utils.easyToObj
import com.orhanobut.logger.Logger


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