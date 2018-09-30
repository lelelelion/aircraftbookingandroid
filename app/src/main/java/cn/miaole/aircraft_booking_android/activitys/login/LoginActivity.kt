package cn.miaole.aircraft_booking_android.activitys.login

import android.content.Context
import android.content.Intent
import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.activitys.forget_pwd.ForgetPwdActivity
import cn.miaole.aircraft_booking_android.activitys.main.MainActivity
import cn.miaole.aircraft_booking_android.activitys.register.RegisterActivity
import cn.miaole.aircraft_booking_android.extensions.jumpForResult
import cn.miaole.aircraft_booking_android.extensions.jumpTo
import cn.miaole.aircraft_booking_android.extensions.sendCode
import cn.miaole.aircraft_booking_android.model.internet.data.LoginResponseData
import cn.miaole.aircraft_booking_android.model.params.IntentParam
import cn.miaole.aircraft_booking_android.utils.easyToJson
import cn.smssdk.EventHandler
import cn.smssdk.SMSSDK
import cn.smssdk.gui.RegisterPage
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_login.*
import kotlinx.android.synthetic.main.bar_item.*
import org.jetbrains.anko.design.snackbar

class LoginActivity : MVPBaseActivity<LoginActivityPresenter>(), LoginActivityContract.View {

    companion object {
        const val PARAM_PHONE = "phone"
        const val PARAM_COUNTRY = "country"
    }

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
            jumpForResult(ForgetPwdActivity::class.java, 0)
        }
        tvRegisterNow.setOnClickListener {
            sendCode({
                //验证失败
                tvRegisterNow.snackbar(R.string.vertify_fail)
            }) { phone, country ->
                //验证成功
                jumpForResult(RegisterActivity::class.java, 0, IntentParam()
                        .add(PARAM_PHONE, phone)
                        .add(PARAM_COUNTRY, country)
                )
            }
        }

        //登陆
        btnRegister.setOnClickListener {
            mPresenter.login(etUsername.text.toString(), etPassword.text.toString())
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        super.onActivityResult(requestCode, resultCode, data)
        when (requestCode) {
            0 -> {          //注册成功的回调
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
