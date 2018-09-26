package cn.miaole.aircraft_booking_android.model.internet.services

import cn.miaole.aircraft_booking_android.model.internet.api.ApiInfo
import cn.miaole.aircraft_booking_android.model.internet.data.LoginResponseData
import cn.miaole.aircraft_booking_android.model.internet.data.ResponseBody
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.FormUrlEncoded
import retrofit2.http.POST

interface UserService {

    @FormUrlEncoded
    @POST(ApiInfo.LOGIN_URL)
    fun login(
            @Field(ApiInfo.LOGIN_PARAM_USERNAME) username: String,
            @Field(ApiInfo.LOGIN_PARAM_PASSWORD) password: String
    ): Observable<ResponseBody<LoginResponseData>>
}