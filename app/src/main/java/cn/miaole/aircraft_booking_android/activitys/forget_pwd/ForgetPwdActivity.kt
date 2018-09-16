package cn.miaole.aircraft_booking_android.activitys.forget_pwd

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*

class ForgetPwdActivity : MVPBaseActivity<ForgetPwdActivityPresenter>(), ForgetPwdActivityContract.View {
    override fun onCretePresenter(): ForgetPwdActivityPresenter =
            ForgetPwdActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_forget_pwd)

        initView()
    }

    private fun initView() {
        easyBar.init(mode = EasyBar.Mode.ICON_, title = "忘记密码", leftCallback = {
            onBackPressed()
        })
    }
}
