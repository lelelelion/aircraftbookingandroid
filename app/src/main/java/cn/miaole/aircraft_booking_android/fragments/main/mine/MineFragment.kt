package cn.miaole.aircraft_booking_android.fragments.main.mine

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.fragment.MVPBaseFragment
import cn.miaole.aircraft_booking_android.activitys.login.LoginActivity
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*
import kotlinx.android.synthetic.main.fragment_main_mine.*

class MineFragment: MVPBaseFragment<MineFragmentPresenter>(), MineFragmentContract.View{

    companion object {
        fun newInstance(): MineFragment {
            val args = Bundle()
            val fragment = MineFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreatePresenter(): MineFragmentPresenter =
            MineFragmentPresenter(this)

    override fun getRes(): Int = R.layout.fragment_main_mine

    override fun initView() {
        easyBar.init(mode = EasyBar.Mode.NONE, titleRes = R.string.mine)

        lmiMyOrder.setOnClickListener {         //我的订单

        }

        lmiMyTrip.setOnClickListener {          //我的行程

        }

        lmiAboutUs.setOnClickListener {         //关于我们

        }

        lmiPassengerInfo.setOnClickListener {   //乘机人信息

        }

        lmiLogout.setOnClickListener {      //退出登陆
            activity?.jumpTo(LoginActivity::class.java)
        }
    }

    override fun initialLoadData() {
    }

}