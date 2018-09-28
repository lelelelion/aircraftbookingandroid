package cn.miaole.aircraft_booking_android.model.internet.rx

import android.content.Context
import android.support.annotation.CallSuper
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.model.event.RefreshEvent
import com.orhanobut.logger.Logger
import io.reactivex.Observer
import io.reactivex.disposables.Disposable
import org.greenrobot.eventbus.EventBus

abstract class RxObserver<T>(private val mp: BasePresenter<*, *>? = null,
                             private val context: Context? = null) : Observer<T> {
    /**
     * 一般不在这个回调里面处理数据，所以默认给一个空实现
     */
    override fun onComplete() {

    }

    override fun onSubscribe(d: Disposable) {
        mp?.addSubscription(d)
    }


    @CallSuper
    override fun onError(e: Throwable) {
        e.printStackTrace()
        Logger.e(e, "网络请求错误")

        //出现错误则发送一个刷新完毕事件，这样可以优雅的关闭
        EventBus.getDefault()
                .post(RefreshEvent())

        //todo 下面对错误进行统一处理
        _onError(e.message ?: "")
    }

    override fun onNext(t: T) {
        _onNext(t)
    }


    abstract fun _onNext(t: T)

    open fun _onError(msg: String) {

    }

}