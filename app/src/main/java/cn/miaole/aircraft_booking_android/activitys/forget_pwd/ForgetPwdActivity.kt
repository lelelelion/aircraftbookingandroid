package cn.miaole.aircraft_booking_android.activitys.forget_pwd

import android.content.Intent
import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.activitys.register.RegisterActivity
import cn.miaole.aircraft_booking_android.extensions.setStyleText
import cn.miaole.aircraft_booking_android.extensions.toast
import cn.miaole.aircraft_booking_android.utils.AccountValidatorUtil
import cn.miaole.aircraft_booking_android.utils.doInterval
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_forget_pwd.*
import kotlinx.android.synthetic.main.bar_item.*
import org.jetbrains.anko.design.snackbar

class ForgetPwdActivity : MVPBaseActivity<ForgetPwdActivityPresenter>(), ForgetPwdActivityContract.View {
    override fun generateNewPasswordSuccess(phone: String) {
        // 修改密码成功，跳到登陆界面
        Intent().apply {
            putExtra(RegisterActivity.REGISTER_USERNAME, phone)
            putExtra(RegisterActivity.REGISTER_PASSWORD, "")
            setResult(0, this)
            this@ForgetPwdActivity.toast(R.string.modify_password_success)
            this@ForgetPwdActivity.finish()
        }
    }

    override fun sendCodeSuccess() {
        tvSendVertifyCode.isClickable = false
        doInterval(count = 60, start = 1) {
            if (it == 60L) {
                tvSendVertifyCode.setStyleText(textRes = R.string.send_vertify_code, colorRes = R.color.colorPrimary)
                tvSendVertifyCode.isClickable = true
                return@doInterval
            }
            tvSendVertifyCode.setStyleText(text = getString(R.string.send_code_after_several_second, (60 - it).toString()),
                    colorRes = R.color.grey)
        }
    }

    override fun sendCodeFail(msg: String) {
        easyBar.snackbar(R.string.verification_send_fail)
    }

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

        btnConfirm.setOnClickListener {
            //执行忘记密码接口
            val phone = etPhone.text.toString()
            val newPassword = etPassword.text.toString()
            val newRePassword = etRePassword.text.toString()
            val code = etVertifyCode.text.toString()
            if (phone == "" || newPassword == "" || newRePassword == "" || code == "") {
                btnConfirm.snackbar(R.string.please_complete_info)
                return@setOnClickListener
            }
            if (newPassword != newRePassword) {
                btnConfirm.snackbar(R.string.twice_password_not_same)
                return@setOnClickListener
            }

            //执行验证，试图修改密码
            mPresenter.forgetPassword(phone, newPassword, code)
        }

        tvSendVertifyCode.setOnClickListener {
            val phone = etPhone.text.toString()
            if (phone == "") {
                tvSendVertifyCode.snackbar(R.string.please_complete_info)
                return@setOnClickListener
            }
            if (!AccountValidatorUtil.isMobile(phone)) {
                tvSendVertifyCode.snackbar(R.string.please_input_validate_phone_number)
                return@setOnClickListener
            }
            mPresenter.sendVerificationCode("86", phone)
        }
    }

}
