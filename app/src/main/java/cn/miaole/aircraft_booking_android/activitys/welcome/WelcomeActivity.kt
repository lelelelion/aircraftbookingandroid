package cn.miaole.aircraft_booking_android.activitys.welcome

import android.graphics.Color
import android.os.Build
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import android.widget.ImageView
import android.widget.TimePicker
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.activitys.login.LoginActivity
import cn.miaole.aircraft_booking_android.activitys.main.MainActivity
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import kotlinx.android.synthetic.main.activity_welcome.*

class WelcomeActivity : MVPBaseActivity<WelcomeActivityPresenter>(),
        WelcomeActivityContract.View {

    override fun onCretePresenter(): WelcomeActivityPresenter =
            WelcomeActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_welcome)

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            val window = window
            window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS or WindowManager.LayoutParams.FLAG_TRANSLUCENT_NAVIGATION)
            window.decorView.systemUiVisibility = (View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN
                    or View.SYSTEM_UI_FLAG_LAYOUT_HIDE_NAVIGATION
                    or View.SYSTEM_UI_FLAG_LAYOUT_STABLE)
            window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
            window.statusBarColor = Color.TRANSPARENT
            window.navigationBarColor = Color.TRANSPARENT
        }

        welcome_layout.postDelayed({
            jumpTo(MainActivity::class.java)
            finish()
        }, 200)
    }
}
