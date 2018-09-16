package cn.miaole.aircraft_booking_android.activitys.base.fragment

import android.os.Bundle
import cn.miaole.aircraft_booking_android.activitys.base.ABAFragment
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter

abstract class MVPBaseFragment<P: BasePresenter<*, *>>: ABAFragment(){

    protected lateinit var mPresenter: P
    abstract fun onCreatePresenter(): P

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mPresenter = onCreatePresenter()
    }
}