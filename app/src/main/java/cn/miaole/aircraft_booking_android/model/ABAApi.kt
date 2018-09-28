package cn.miaole.aircraft_booking_android.model

import android.annotation.SuppressLint
import android.content.Context
import cn.miaole.aircraft_booking_android.model.internet.data.User
import cn.miaole.aircraft_booking_android.utils.SpUtils
import cn.miaole.aircraft_booking_android.utils.easyToJson
import cn.miaole.aircraft_booking_android.utils.easyToObj
import org.jetbrains.anko.buildSpanned

@SuppressLint("StaticFieldLeak")
object ABAApi {
    private const val AUTHORIZATION_TOKEN = "AUTHORIZATION_TOKEN"
    private const val IS_LOGIN = "IS_LOGIN"
    private const val USER_INFO = "USER_INFO"

    var context: Context? = null
    var authorizationToken = ""
    var isLogin = false
    var userInfo: User? = null

    fun bind(context: Context) {
        this.context = context
        if (SpUtils.contains(context, AUTHORIZATION_TOKEN))
            authorizationToken = SpUtils.get(context, AUTHORIZATION_TOKEN, "")
        if (SpUtils.contains(context, IS_LOGIN))
            isLogin = SpUtils.get(context, IS_LOGIN, false)
        if(SpUtils.contains(context, USER_INFO))
            userInfo = SpUtils.get(context, USER_INFO, "").easyToObj(User::class.java)
    }

    fun updateAuthorizationToken(token: String) {
        this.authorizationToken = token
        SpUtils.put(context, AUTHORIZATION_TOKEN, token)
    }

    fun updateIsLogin(isLogin: Boolean){
        this.isLogin = isLogin
        SpUtils.put(context, IS_LOGIN, isLogin)
    }

    fun updateUserInfo(user: User?){
        userInfo = user
        user?.let {
            SpUtils.put(context, USER_INFO, it.easyToJson())
        }
    }
}