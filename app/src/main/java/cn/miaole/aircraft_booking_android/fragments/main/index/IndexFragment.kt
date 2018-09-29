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
import cn.miaole.aircraft_booking_android.utils.easyToJson
import com.orhanobut.logger.Logger


class IndexFragment : MVPBaseFragment<IndexFragmentPresenter>(), IndexFragmentContract.View {

    companion object {
        fun newInstance(): IndexFragment {
            val args = Bundle()
            val fragment = IndexFragment()
            fragment.arguments = args
            return fragment
        }
    }

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


        btnSearch.setOnClickListener {
        }

    }


    override fun initialLoadData() {
    }

}