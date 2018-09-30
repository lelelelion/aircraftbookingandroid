package cn.miaole.aircraft_booking_android.model.internet.rx

import cn.miaole.aircraft_booking_android.model.ABAApi
import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.ResponseBody
import cn.miaole.aircraft_booking_android.model.internet.exceptions.JWTExpireException
import cn.miaole.aircraft_booking_android.model.internet.exceptions.NotLoginException
import cn.miaole.aircraft_booking_android.model.internet.exceptions.OtherServerException
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class RxResultHelper {
    companion object {
        fun <T> handleResult(): ObservableTransformer<ResponseBody<T>, T> {
            return ObservableTransformer { upstream ->
                upstream.flatMap<T> { result ->
                    try {
                        when (result.code) {
                            0 -> return@flatMap Observable.just(result.data)
                            -1 -> return@flatMap Observable.error(OtherServerException(result.msg))

                            //JWT 令牌失效
                            -3 -> return@flatMap Observable.error(JWTExpireException(result.msg))

                            //未登陆
                            -4 -> return@flatMap Observable.error(NotLoginException(result.msg))

                            else -> return@flatMap Observable.empty<T>()
                        }
                    } catch (e: Exception) {          //服务器如果没有返回体，则result可能为空
                        return@flatMap Observable.error(OtherServerException(e.message ?: ""))
                    }
                }.retryWhen { t: Observable<Throwable> ->
                    //如果发现登录过期，则用token更新cookie后重试
                    t.flatMap {
                        if (it is JWTExpireException) {
                            return@flatMap APIManager
                                    .updateToken()
                                    .compose(RxSchedulersHelper.io_main())
                                    .compose(RxResultHelper.handleResult())
                                    .flatMap {
                                        ABAApi.updateIsLogin(true)
                                        ABAApi.updateAuthorizationToken(it.token)
                                        return@flatMap Observable.just(1)
                                    }

                        }
                        return@flatMap Observable.error<T>(it)
                    }
                }
            }
        }
    }
}