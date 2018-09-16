package cn.miaole.aircraft_booking_android.activitys.base.activity

import android.os.Bundle
import android.support.annotation.CallSuper
import cn.miaole.aircraft_booking_android.activitys.base.ABAActivity
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter

abstract class MVPBaseActivity<P : BasePresenter<*, *>> : ABAActivity() {
    protected lateinit var mPresenter: P
    @CallSuper
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = onCretePresenter()
    }

    abstract fun onCretePresenter(): P
}