package cn.miaole.aircraft_booking_android.activitys.register

import android.content.Intent
import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.activitys.login.LoginActivity
import cn.miaole.aircraft_booking_android.extensions.hideSoftKeyboard
import cn.miaole.aircraft_booking_android.extensions.toast
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_register.*
import kotlinx.android.synthetic.main.bar_item.*
import org.jetbrains.anko.design.snackbar

class RegisterActivity : MVPBaseActivity<RegisterActivityPresenter>(), RegisterActivityContract.View {

    companion object {
        const val REGISTER_USERNAME = "username"
        const val REGISTER_PASSWORD = "password"
    }
    override fun registerSuccess(phone: String, password: String) {
        Intent().apply {
            putExtra(REGISTER_USERNAME, phone)
            putExtra(REGISTER_PASSWORD, password)
            setResult(0, this)
            this@RegisterActivity.toast(R.string.register_success)
            this@RegisterActivity.finish()
        }
    }

    override fun registerFail(msg: String) {
        btnRegister.snackbar(msg)
    }

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

        val phone = intent.getStringExtra(LoginActivity.PARAM_PHONE)
        val country = intent.getStringExtra(LoginActivity.PARAM_COUNTRY)
        btnRegister.setOnClickListener {
            hideSoftKeyboard(it)
            mPresenter.register(phone, etPassword.text.toString(),
                    etRePassword.text.toString())
        }
    }
}
