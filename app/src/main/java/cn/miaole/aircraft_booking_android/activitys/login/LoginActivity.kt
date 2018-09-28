package cn.miaole.aircraft_booking_android.activitys.login

import android.content.Intent
import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.activitys.forget_pwd.ForgetPwdActivity
import cn.miaole.aircraft_booking_android.activitys.main.MainActivity
import cn.miaole.aircraft_booking_android.activitys.register.RegisterActivity
import cn.miaole.aircraft_booking_android.extensions.jumpForResult
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import cn.miaole.aircraft_booking_android.model.internet.data.LoginResponseData
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.bar_item.*

class LoginActivity : MVPBaseActivity<LoginActivityPresenter>(), LoginActivityContract.View {

    override fun loginSuccess(loginResponseData: LoginResponseData) {
        jumpTo(MainActivity::class.java)
        finish()
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
            jumpForResult(RegisterActivity::class.java, 0)
        }

        //登陆
        btnRegister.setOnClickListener {
            mPresenter.login(etUsername.text.toString(), etPassword.text.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when(requestCode){
            0 -> {          //注册成功的回调
                Logger.i("Register Result")
                data?.apply {
                    val username = getStringExtra(RegisterActivity.REGISTER_USERNAME)
                    val password = getStringExtra(RegisterActivity.REGISTER_PASSWORD)
                    etUsername.setText(username)
                    etPassword.setText(password)
                }
            }
        }
    }
}
