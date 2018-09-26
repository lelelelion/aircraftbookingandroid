package cn.miaole.aircraft_booking_android.activitys.login

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.activitys.forget_pwd.ForgetPwdActivity
import cn.miaole.aircraft_booking_android.activitys.register.RegisterActivity
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import cn.miaole.aircraft_booking_android.model.internet.data.LoginResponseData
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.bar_item.*

class LoginActivity : MVPBaseActivity<LoginActivityPresenter>(), LoginActivityContract.View {
    override fun loginSuccess(loginResponseData: LoginResponseData) {

    }

    override fun onCretePresenter(): LoginActivityPresenter =
            LoginActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)

        initView()
    }

    private fun initView() {
        easyBar.init(mode = EasyBar.Mode.ICON_, title = getString(R.string.login), leftCallback = {
            onBackPressed()
        })
        tvForgetPwd.setOnClickListener {
            jumpTo(ForgetPwdActivity::class.java)
        }
        tvRegisterNow.setOnClickListener {
            jumpTo(RegisterActivity::class.java)
        }

        //登陆
        btnLogin.setOnClickListener {
            mPresenter.login(etUsername.text.toString(), etPassword.text.toString())
        }
    }
}
