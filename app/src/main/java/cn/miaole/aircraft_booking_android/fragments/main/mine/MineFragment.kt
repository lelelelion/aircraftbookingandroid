package cn.miaole.aircraft_booking_android.fragments.main.mine

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.about_us.AboutUsActivity
import cn.miaole.aircraft_booking_android.activitys.base.fragment.MVPBaseFragment
import cn.miaole.aircraft_booking_android.activitys.login.LoginActivity
import cn.miaole.aircraft_booking_android.activitys.my_order.MyOrderActivity
import cn.miaole.aircraft_booking_android.activitys.my_trip.MyTripActivity
import cn.miaole.aircraft_booking_android.activitys.passengers.PassengersActivity
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import cn.miaole.aircraft_booking_android.extensions.loadCircle
import cn.miaole.aircraft_booking_android.model.ABAApi
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.bar_item.*
import kotlinx.android.synthetic.main.fragment_main_mine.*

class MineFragment : MVPBaseFragment<MineFragmentPresenter>(), MineFragmentContract.View {
    override fun logoutSuccess() {
        activity?.apply {
            jumpTo(LoginActivity::class.java)
        }
    }

    companion object {
        fun newInstance(): MineFragment {
            val args = Bundle()
            val fragment = MineFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onResume() {
        super.onResume()
        tvUsername.post {
            if (ABAApi.isLogin) {
                ABAApi.userInfo?.apply {
                    tvUsername.text = username
                    val str = if (avatar == "")     //默认头像
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTESwK4T8ZwCO1iFj5KfEn0GmVCBapK4vS45O70d8D9CTl5XoGW"
                    else
                        ""
                    imgAvatar.loadCircle(str)
                }
            } else {
                tvUsername.setText(R.string.click_here_to_login)
                imgAvatar.loadCircle(R.drawable.blue_thumb)
            }
        }
    }

    override fun onCreatePresenter(): MineFragmentPresenter =
            MineFragmentPresenter(this)

    override fun getRes(): Int = R.layout.fragment_main_mine

    override fun initView() {
        easyBar.init(mode = EasyBar.Mode.NONE, titleRes = R.string.mine)

        clUser.setOnClickListener {
            if(ABAApi.isLogin){

            } else {
                activity?.jumpTo(LoginActivity::class.java)
            }
        }

        lmiMyOrder.setOnClickListener {
            //我的订单
            activity?.jumpTo(MyOrderActivity::class.java)
        }

        lmiMyTrip.setOnClickListener {
            //我的行程
            activity?.jumpTo(MyTripActivity::class.java)
        }

        lmiAboutUs.setOnClickListener {
            //关于我们
            activity?.jumpTo(AboutUsActivity::class.java)
        }

        lmiPassengerInfo.setOnClickListener {
            //乘机人信息
            activity?.jumpTo(PassengersActivity::class.java)
        }

        lmiLogout.setOnClickListener {
            //退出登陆
            mPresenter.logout()
        }
    }

    override fun initialLoadData() {
    }

}