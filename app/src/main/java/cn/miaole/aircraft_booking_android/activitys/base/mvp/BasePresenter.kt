package cn.miaole.aircraft_booking_android.activitys.base.mvp

import android.content.Context
import android.support.annotation.StringRes
import android.widget.Toast
import cn.miaole.aircraft_booking_android.extensions.toast
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.disposables.Disposable

abstract class BasePresenter<V : BaseView, M : BaseModel> {
    protected lateinit var mView: V
    protected lateinit var mModel: M

    // 用来保存订阅，便于统一取消
    private val compositeDisposable: CompositeDisposable = CompositeDisposable()

    /**
     * 添加Rx订阅
     */
    fun addSubscription(disposable: Disposable) {
        compositeDisposable.add(disposable)
    }

    /**
     * 移除单个订阅
     */
    fun removeSubscription(disposable: Disposable) {
        compositeDisposable.remove(disposable)
    }

    /**
     * 清除所有订阅
     */
    fun clearAllSubscription(){
        compositeDisposable.clear()
    }

    fun toast(info: String, duration: Int = Toast.LENGTH_SHORT) {
        if (mView is Context)
            (mView as Context).toast(info, duration)
    }

    fun toast(@StringRes stringRes: Int, duration: Int = Toast.LENGTH_SHORT) {
        if (mView is Context)
            (mView as Context).toast(stringRes, duration)
    }
}

