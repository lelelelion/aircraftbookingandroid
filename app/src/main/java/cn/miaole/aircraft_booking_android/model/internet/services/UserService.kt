package cn.miaole.aircraft_booking_android.model.internet.services

import cn.miaole.aircraft_booking_android.model.internet.api.ApiInfo
import cn.miaole.aircraft_booking_android.model.internet.data.*
import io.reactivex.Observable
import retrofit2.http.*

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


    @GET(ApiInfo.UPDATE_TOKEN_URL)
    fun updateToken(
            @Header(ApiInfo.REQUIRE_HEADER_AUTHORIZARION) token: String
    ): Observable<ResponseBody<UpdateTokenResponseData>>

    /**
     * 获取乘机人信息
     */
    @GET(ApiInfo.GET_PASSENGER_CONTACTS_URL)
    fun getPassengerContacts(
            @Header(ApiInfo.REQUIRE_HEADER_AUTHORIZARION) token: String
            ): Observable<ResponseBody<List<Passenger>>>

    /**
     * 添加乘机人信息
     */
    @FormUrlEncoded
    @POST(ApiInfo.ADD_PASSENGER_CONTACT_URL)
    fun addPassengerContact(
            @Header(ApiInfo.REQUIRE_HEADER_AUTHORIZARION) token: String,
            @Field(ApiInfo.ADD_PASSENGER_CONTACT_PARAM_NAME) name: String,
            @Field(ApiInfo.ADD_PASSENGER_CONTACT_PARAM_CERTIFICATE_TYPE) certificateType: Int,
            @Field(ApiInfo.ADD_PASSENGER_CONTACT_PARAM_CERTIFICATE_VALUE) certificateValue: String,
            @Field(ApiInfo.ADD_PASSENGER_CONTACT_PARAM_PHONE) phone: String,
            @Field(ApiInfo.ADD_PASSENGER_CONTACT_PARAM_EMAIL) email: String,
            @Field(ApiInfo.ADD_PASSENGER_CONTACT_IS_ADULT) isAdult: Boolean
    ): Observable<ResponseBody<Passenger>>
}