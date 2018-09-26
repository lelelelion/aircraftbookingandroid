package cn.miaole.aircraft_booking_android.model.internet.rx

import cn.miaole.aircraft_booking_android.model.internet.data.ResponseBody
import cn.miaole.aircraft_booking_android.model.internet.exceptions.OtherServerException
import io.reactivex.Observable
import io.reactivex.ObservableTransformer

class RxResultHelper {
    companion object {
        fun <T> handleResult(): ObservableTransformer<ResponseBody<T>, T> {
            return ObservableTransformer { upstream ->
                upstream.flatMap<T> { result ->
                    try {
                        when(result.code){
                            0 -> return@flatMap Observable.just(result.data)
                            -1 -> return@flatMap Observable.error(OtherServerException(result.msg))
                            else -> return@flatMap Observable.empty<T>()
                        }
                    } catch (e: NullPointerException) {          //服务器如果没有返回体，则result可能为空
                        return@flatMap Observable.error(OtherServerException(e.message ?: ""))
                    }
                }.retryWhen { t: Observable<Throwable> ->
                    //如果发现登录过期，则用token更新cookie后重试
                    t.flatMap {
//                        if(it is LoginInvalidException){
//                            val body = createRequestBody(RequestBodyParam()
//                                    .add(ApiInfo.UPDATE_COOKIE_BY_TOKEN_TOKEN,
//                                            UserInfoManager.INSTANCE.getUserInfo().loginToken))
//                            return@flatMap APIManager.INSTANCE
//                                    .updateCookieByToken(body!!)
//                                    .compose(RxSchedulersHelper.io_main())
//                                    .compose(RxResultHelper.handleResult())
//                                    .flatMap {
//                                        YouyunAPI.INSTANCE.updateIsLogin(true)
//                                        YouyunAPI.INSTANCE.updateLoginToken(it.loginToken)
//                                        UserInfoManager.INSTANCE.updateUserInfo(it)
//                                        Logger.i("更新cookie成功： ${GsonUtil.bean2Json(it)}")
//                                        return@flatMap Observable.just(1)
//                                    }
//
//                        }
                        return@flatMap Observable.error<T>(it)
                    }
                }
            }
        }
    }
}