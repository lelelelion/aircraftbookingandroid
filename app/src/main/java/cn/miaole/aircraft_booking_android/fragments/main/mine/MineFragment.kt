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
import cn.miaole.aircraft_booking_android.extensions.snackLoginTip
import cn.miaole.aircraft_booking_android.model.ABAApi
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*
import kotlinx.android.synthetic.main.fragment_main_mine.*
import org.jetbrains.anko.design.snackbar

class MineFragment : MVPBaseFragment<MineFragmentPresenter>(), MineFragmentContract.View {
    override fun getUserInfoSuccess() {
        fillInUserInfo()
    }

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
        fillInUserInfo()
        mPresenter.getUserInfo()
    }

    private fun fillInUserInfo() {
        tvUsername.post {
            if (ABAApi.isLogin) {
                ABAApi.userInfo?.apply {
                    tvUsername.text = username
                    val str = if (avatar == "")     //默认头像
                        "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTESwK4T8ZwCO1iFj5KfEn0GmVCBapK4vS45O70d8D9CTl5XoGW"
                    else
                        ""
                    imgAvatar.loadCircle(str)
                    tvRemainMoney.text = getString(R.string.remain_money, money.toString())
                }
            } else {
                tvUsername.setText(R.string.click_here_to_login)
                tvRemainMoney.text = getString(R.string.remain_money, "0")
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
            if (ABAApi.isLogin) {

            } else {
                activity?.jumpTo(LoginActivity::class.java)
            }
        }

        lmiMyOrder.setOnClickListener {
            if (ABAApi.isLogin) {
                //我的订单
                activity?.jumpTo(MyOrderActivity::class.java)
            } else {
                activity?.snackLoginTip(easyBar)
            }

        }

        lmiMyTrip.setOnClickListener {
            if (ABAApi.isLogin) {
                //我的行程
                activity?.jumpTo(MyTripActivity::class.java)
            } else {
                activity?.snackLoginTip(easyBar)
            }
        }

        lmiAboutUs.setOnClickListener {
            //关于我们
            activity?.jumpTo(AboutUsActivity::class.java)
        }

        lmiPassengerInfo.setOnClickListener {
            if (ABAApi.isLogin) {
                //乘机人信息
                activity?.jumpTo(PassengersActivity::class.java)
            } else {
                activity?.snackLoginTip(easyBar)
            }

        }

        lmiLogout.setOnClickListener {
            //退出登陆
            mPresenter.logout()
        }
    }

    override fun initialLoadData() {
    }

}