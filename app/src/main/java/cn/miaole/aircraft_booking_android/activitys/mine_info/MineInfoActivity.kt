package cn.miaole.aircraft_booking_android.activitys.mine_info

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.extensions.loadCircle
import cn.miaole.aircraft_booking_android.extensions.toYMD
import cn.miaole.aircraft_booking_android.model.ABAApi
import cn.miaole.aircraft_booking_android.model.internet.data.User
import com.j.ming.dcim.extensions.load
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_mine_info.*
import kotlinx.android.synthetic.main.bar_item.*

class MineInfoActivity : MVPBaseActivity<MineInfoActivityPresenter>(),
        MineInfoActivityContract.View {

    override fun onCretePresenter(): MineInfoActivityPresenter =
            MineInfoActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_mine_info)
        initView()
    }

    private lateinit var userInfo: User

    private fun initView() {
        easyBar.init(mode = EasyBar.Mode.ICON, titleRes = R.string.my_info, leftCallback = {
            onBackPressed()
        }, rightRes = R.drawable.icon_edit)
        intent.apply {
            userInfo = ABAApi.userInfo!!
        }

        tvName.text = if (userInfo.nickname != "")
            userInfo.nickname
        else
            userInfo.username

        tvMoney.text = getString(R.string.price, userInfo.money.toString())

        val str = if (userInfo.avatar == "")     //默认头像
            "https://encrypted-tbn0.gstatic.com/images?q=tbn:ANd9GcTESwK4T8ZwCO1iFj5KfEn0GmVCBapK4vS45O70d8D9CTl5XoGW"
        else
            ""
        imgAvatar.loadCircle(str)

        if (userInfo.birthday != 0L) {
            tvBirthday.text = userInfo.birthday.toYMD()
        } else {
            tvBirthday.text = getString(R.string.unknow)
        }

        if (userInfo.phone != "") {
            tvPhone.text = userInfo.phone
        } else {
            tvPhone.text = getString(R.string.unknow)
        }

        if (userInfo.email != "") {
            tvEmail.text = userInfo.email
        } else {
            tvEmail.text = getString(R.string.unknow)
        }

        if (userInfo.gender == 0) {
            imgGender.load(R.drawable.man)
        } else {
            imgGender.load(R.drawable.icon_femael)
        }
    }
}
