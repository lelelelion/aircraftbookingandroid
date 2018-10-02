package cn.miaole.aircraft_booking_android.activitys.edit_person_info

import android.content.Intent
import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.extensions.showSelectDateDialog
import cn.miaole.aircraft_booking_android.extensions.toTimeStamp
import cn.miaole.aircraft_booking_android.extensions.toYMD
import cn.miaole.aircraft_booking_android.extensions.toast
import cn.miaole.aircraft_booking_android.model.ABAApi
import cn.miaole.aircraft_booking_android.utils.AccountValidatorUtil
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_edit_person_info.*
import kotlinx.android.synthetic.main.bar_item.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.selector

class EditPersonInfoActivity : MVPBaseActivity<EditPersonInfoActivityPresenter>(),
        EditPersonInfoActivityContract.View {
    override fun updateUserInfoSuccess() {
        easyBar.snackbar(R.string.update_info_success)
        setResult(0, Intent())
        finish()
    }

    override fun onCretePresenter(): EditPersonInfoActivityPresenter =
            EditPersonInfoActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_edit_person_info)

        initView()
    }

    private fun initView() {
        easyBar.init(mode = EasyBar.Mode.ICON_TEXT, titleRes = R.string.modify_mine_info, leftCallback = {
            onBackPressed()
        }, rightText = getString(R.string.save), rightCallback = {
            val phone = etPhone.text.toString()
            val email = etEmail.text.toString()
            var birthday = 0L
            etBirthday.text.toString().apply {
                birthday = if (this == "")
                    0L
                else
                    this.toTimeStamp()
            }
            val gender = if (etGender.text.toString() == "男")
                0
            else
                1
            val nickname = etNickname.text.toString()
            if (phone != "" && !AccountValidatorUtil.isMobile(phone)) {
                easyBar.snackbar(R.string.please_input_validate_phone_number)
                return@init
            }
            if (email != "" && !AccountValidatorUtil.isEmail(email)) {
                easyBar.snackbar(R.string.please_input_validate_email)
                return@init
            }
            mPresenter.updateUserInfo(phone, nickname, email, gender, birthday)
        })
        fillInfo()

        etGender.setOnClickListener {
            val genders = listOf("男", "女")
            selector(getString(R.string.select_gender), genders) { dialogInterface, i ->
                etGender.setText(genders[i])
            }
        }

        imgCalendar.setOnClickListener {
            //选择日期
            showSelectDateDialog { time ->
                etBirthday.setText(time.toYMD())
            }
        }
    }

    private fun fillInfo() {
        ABAApi.userInfo?.apply {
            etNickname.setText(if (nickname == "")
                username
            else
                nickname)
            if (birthday != 0L)
                etBirthday.setText(birthday.toYMD())
            etGender.setText(if (gender == 0)
                "男"
            else
                "女")
            etPhone.setText(phone)
            etEmail.setText(email)
        }
    }
}
