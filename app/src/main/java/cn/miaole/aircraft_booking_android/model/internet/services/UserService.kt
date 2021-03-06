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

    /**
     * Token 失效后更新
     */
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

    /**
     * 更新乘机人信息
     */
    @FormUrlEncoded
    @POST(ApiInfo.UPDATE_PASSENGER_CONTACT_URL)
    fun updatePassengerContact(
            @Header(ApiInfo.REQUIRE_HEADER_AUTHORIZARION) token: String,
            @Field(ApiInfo.UPDATE_PASSENGER_CONTACT_ID) id: String,
            @Field(ApiInfo.UPDATE_PASSENGER_CONTACT_PARAM_NAME) name: String,
            @Field(ApiInfo.UPDATE_PASSENGER_CONTACT_PARAM_CERTIFICATE_TYPE) certificateType: Int,
            @Field(ApiInfo.UPDATE_PASSENGER_CONTACT_PARAM_CERTIFICATE_VALUE) certificateValue: String,
            @Field(ApiInfo.UPDATE_PASSENGER_CONTACT_PARAM_PHONE) phone: String,
            @Field(ApiInfo.UPDATE_PASSENGER_CONTACT_PARAM_EMAIL) email: String,
            @Field(ApiInfo.UPDATE_PASSENGER_CONTACT_IS_ADULT) isAdult: Boolean
    ): Observable<ResponseBody<Passenger>>


    /**
     * 删除乘机人
     */
    @FormUrlEncoded
    @POST(ApiInfo.DELETE_PASSENGER_CONTACT_URL)
    fun deletePassengerContact(
            @Header(ApiInfo.REQUIRE_HEADER_AUTHORIZARION) token: String,
            @Field(ApiInfo.DELETE_PASSENGER_CONTACT_PARAM_ID) id: String
    ): Observable<ResponseBody<EmptyResponseData>>


    /**
     * 获取订单列表
     */
    @GET(ApiInfo.GET_ORDERS_URL)
    fun getOrders(
            @Header(ApiInfo.REQUIRE_HEADER_AUTHORIZARION) token: String,
            @Query(ApiInfo.GET_ORDERS_PARAM_PAGE) page: Int,
            @Query(ApiInfo.GET_ORDERS_PARAM_SIZE) size: Int
    ): Observable<ResponseBody<List<Order>>>

    /**
     * 删除订单
     */
    @FormUrlEncoded
    @POST(ApiInfo.DELETE_ORDER_URL)
    fun deleteOrder(
            @Header(ApiInfo.REQUIRE_HEADER_AUTHORIZARION) token: String,
            @Field(ApiInfo.DELETE_ORDER_PARAM_ORDER_ID) orderId: String
    ): Observable<ResponseBody<EmptyResponseData>>


    /**
     * 获取用户信息
     */
    @GET(ApiInfo.GET_USER_INFO_URL)
    fun getUserInfo(
            @Header(ApiInfo.REQUIRE_HEADER_AUTHORIZARION) token: String
    ): Observable<ResponseBody<User>>

    /**
     * 忘记密码
     */
    @FormUrlEncoded
    @POST(ApiInfo.FORGET_PASSWORD_URL)
    fun forgetPassword(
            @Field(ApiInfo.FORGET_PASSWORD_PARAM_PHONE) phone: String,
            @Field(ApiInfo.FORGET_PASSWORD_PARAM_NEW_PASSWORD) newPassword: String,
            @Field(ApiInfo.FORGET_PASSWORD_PARAM_CODE) code: String,
            @Field(ApiInfo.FORGET_PASSWORD_PARAM_ZONE) zone: String
    ): Observable<ResponseBody<EmptyResponseData>>

    /**
     * 获取我的行程
     */
    @GET(ApiInfo.GET_TRIPS_URL)
    fun getTrips(
            @Header(ApiInfo.REQUIRE_HEADER_AUTHORIZARION) token: String,
            @Query(ApiInfo.GET_TRIPS_PARAM_PAGE) page: Int,
            @Query(ApiInfo.GET_TRIPS_PARAM_SIZE) size: Int
    ): Observable<ResponseBody<List<Order>>>

    /**
     * 更新用户信息
     */
    @FormUrlEncoded
    @POST(ApiInfo.UPDATE_USER_INFO_URL)
    fun updateUserInfo(
            @Header(ApiInfo.REQUIRE_HEADER_AUTHORIZARION) token: String,
            @Field(ApiInfo.UPDATE_USER_INFO_PARAM_PHONE) phone: String,
            @Field(ApiInfo.UPDATE_USER_INFO_PARAM_NICKNAME) nickname: String,
            @Field(ApiInfo.UPDATE_USER_INFO_PARAM_EMAIL) email: String,
            @Field(ApiInfo.UPDATE_USER_INFO_PARAM_GENDER) gender: Int,
            @Field(ApiInfo.UPDATE_PASSENGER_CONTACT_PARAM_BIRTHDAY) birthday: Long
    ): Observable<ResponseBody<EmptyResponseData>>
}