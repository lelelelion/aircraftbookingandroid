package cn.miaole.aircraft_booking_android.model.internet.services

import cn.miaole.aircraft_booking_android.model.internet.api.ApiInfo
import cn.miaole.aircraft_booking_android.model.internet.data.LoginResponseData
import cn.miaole.aircraft_booking_android.model.internet.data.RegisterResponseData
import cn.miaole.aircraft_booking_android.model.internet.data.ResponseBody
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {

    /**
     * 登陆
     */
    @FormUrlEncoded
    @POST(ApiInfo.LOGIN_URL)
    fun login(
            @Field(ApiInfo.LOGIN_PARAM_USERNAME) username: String,
            @Field(ApiInfo.LOGIN_PARAM_PASSWORD) password: String
    ): Observable<ResponseBody<LoginResponseData>>

    /**
     * 注册
     */
    @FormUrlEncoded
    @POST(ApiInfo.REGISTER_URL)
    fun register(
            @Field(ApiInfo.REGISTER_PARAM_USERNAME) username: String,
            @Field(ApiInfo.REGISTER_PARAM_PASSWORD) password: String,
            @Field(ApiInfo.REGISTER_PARAM_EMAIL) email: String,
            @Field(ApiInfo.REGISTER_PARAM_NICKNAME) nickname: String,
            @Field(ApiInfo.REGISTER_PARAM_PHONE) phone: String
    ): Observable<ResponseBody<RegisterResponseData>>
}