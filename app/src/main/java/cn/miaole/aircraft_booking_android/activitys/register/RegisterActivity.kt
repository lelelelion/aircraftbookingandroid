package cn.miaole.aircraft_booking_android.activitys.register

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*

class RegisterActivity : MVPBaseActivity<RegisterActivityPresenter>(), RegisterActivityContract.View {
    override fun onCretePresenter(): RegisterActivityPresenter =
            RegisterActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_register)

        initView()
    }

    private fun initView() {
        easyBar.init(mode = EasyBar.Mode.ICON_, title = "注册", leftCallback = {
            onBackPressed()
        })
    }
}
