package cn.miaole.aircraft_booking_android.activitys.add_passenger

import android.os.Bundle
import android.service.autofill.Validators
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.model.internet.data.Passenger
import cn.miaole.aircraft_booking_android.utils.AccountValidatorUtil
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.activity_add_passenger.*
import kotlinx.android.synthetic.main.bar_item.*
import org.jetbrains.anko.design.snackbar
import org.jetbrains.anko.selector
import org.jetbrains.anko.toast

class AddPassengerActivity : MVPBaseActivity<AddPassengerActivityPresenter>(),
        AddPassengerActivityContact.View {
    override fun addPassengerSuccess(passenger: Passenger) {
        toast(R.string.add_success)
        finish()
    }

    //是否是成年人
    private var isAdult = true

    override fun onCretePresenter(): AddPassengerActivityPresenter = AddPassengerActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_passenger)

        initView()
    }

    private fun initView() {
        easyBar.init(mode = EasyBar.Mode.ICON_TEXT, titleRes = R.string.add_passenger, leftCallback = {
            onBackPressed()
        }, rightText = getString(R.string.save), rightCallback = {
            //添加乘机人
            val name = etName.text.toString()
            val idCard = etCertificateValue.text.toString()
            val phone = etPhoneNumber.text.toString()
            val email = etEmail.text.toString()
            if (name == "" || idCard == "") {
                easyBar.snackbar(R.string.please_complete_info)
                return@init
            }
            if (!AccountValidatorUtil.isIDCard(idCard)) {
                easyBar.snackbar(R.string.please_input_validate_id_card)
                return@init
            }
            if (phone != "" && !AccountValidatorUtil.isMobile(phone)) {
                easyBar.snackbar(R.string.please_input_validate_phone_number)
                return@init
            }
            if (email != "" && !AccountValidatorUtil.isEmail(email)) {
                easyBar.snackbar(R.string.please_input_validate_email)
                return@init
            }
            mPresenter.addPassengerContact(name, 0, idCard, isAdult, phone, email)
        })

        liManType.setOnClickListener {
            val types = listOf("成人≥12", "2≤儿童<12")
            selector(getString(R.string.select_passenger_type), types) { dialogInterface, i ->
                liManType.setValue(types[i])
                isAdult = i == 0
            }
        }
    }
}
