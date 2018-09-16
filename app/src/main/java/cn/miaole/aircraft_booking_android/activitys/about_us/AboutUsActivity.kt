package cn.miaole.aircraft_booking_android.activitys.about_us

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*

class AboutUsActivity : MVPBaseActivity<AboutUsACtivityPresenter>(), AboutUsActivityContract.View {
    override fun onCretePresenter(): AboutUsACtivityPresenter =
            AboutUsACtivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_about_us)

        initView()
    }

    private fun initView() {
        easyBar.init(mode = EasyBar.Mode.ICON_, titleRes = R.string.about_us, leftCallback = {
            onBackPressed()
        })
    }
}
