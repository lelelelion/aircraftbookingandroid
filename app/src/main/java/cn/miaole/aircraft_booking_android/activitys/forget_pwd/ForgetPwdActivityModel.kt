package cn.miaole.aircraft_booking_android.activitys.forget_pwd

import android.os.Handler
import cn.smssdk.SMSSDK
import android.os.Looper
import android.os.Message
import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.EmptyResponseData
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper
import cn.smssdk.EventHandler


class ForgetPwdActivityModel(val mPresenter: ForgetPwdActivityPresenter)
    : ForgetPwdActivityContract.Model {
    override fun forgetPassword(phone: String, newPassword: String, code: String, zone: String) {
        APIManager.forgetPassword(phone, newPassword, code, zone)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<EmptyResponseData>(mPresenter) {
                    override fun _onNext(t: EmptyResponseData) {
                        mPresenter.generateNewPasswordSuccess(phone)
                    }
                })
    }

    var eventHandler: EventHandler = object : EventHandler() {
        override fun afterEvent(event: Int, result: Int, data: Any) {
            // afterEvent会在子线程被调用，因此如果后续有UI相关操作，需要将数据发送到UI线程
            val msg = Message()
            msg.arg1 = event
            msg.arg2 = result
            msg.obj = data
            Handler(Looper.getMainLooper(), Handler.Callback { msg ->
                val event = msg.arg1
                val result = msg.arg2
                val data = msg.obj
                if (event == SMSSDK.EVENT_GET_VERIFICATION_CODE) {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        // TODO 处理成功得到验证码的结果
                        // 请注意，此时只是完成了发送验证码的请求，验证码短信还需要几秒钟之后才送达
                        mPresenter.sendCodeSuccess()
                    } else {
                        // TODO 处理错误的结果
                        (data as Throwable).apply {
                            printStackTrace()
                            mPresenter.sendCodeFail(this.message ?: "")
                        }
                    }
                } else if (event == SMSSDK.EVENT_SUBMIT_VERIFICATION_CODE) {
                    if (result == SMSSDK.RESULT_COMPLETE) {
                        // TODO 处理验证码验证通过的结果
                    } else {
                        // TODO 处理错误的结果
                        (data as Throwable).printStackTrace()
                    }
                }
                // TODO 其他接口的返回结果也类似，根据event判断当前数据属于哪个接口
                false
            }).sendMessage(msg)
        }
    }

    init {
        SMSSDK.setAskPermisionOnReadContact(true)
        // 注册一个事件回调，用于处理SMSSDK接口请求的结果
        SMSSDK.registerEventHandler(eventHandler)
    }

    override fun sendVerificationCode(country: String, phone: String) {
        SMSSDK.getVerificationCode("86", phone)
    }
}