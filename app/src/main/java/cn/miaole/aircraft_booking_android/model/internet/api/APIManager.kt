package cn.miaole.aircraft_booking_android.model.internet.api

import android.content.Context
import cn.miaole.aircraft_booking_android.model.ABAApi
import cn.miaole.aircraft_booking_android.model.internet.data.*
import cn.miaole.aircraft_booking_android.model.internet.services.CommonService
import cn.miaole.aircraft_booking_android.model.internet.services.FlightService
import cn.miaole.aircraft_booking_android.model.internet.services.LocationService
import cn.miaole.aircraft_booking_android.model.internet.services.UserService
import com.google.gson.Gson
import com.orhanobut.logger.Logger
import io.reactivex.Observable
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import okhttp3.Response
import retrofit2.Converter
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.Field
import retrofit2.http.Header
import java.nio.charset.Charset
import java.util.concurrent.TimeUnit

object APIManager {
    val UTF8 = Charset.forName("UTF-8")

    /////////////////////////////////////////////////////////
    /////// 静态成员
    /////////////////////////////////////////////////////////
    private val cacheSize = 20 * 1024 * 1024
    //    private static Cache cache = new Cache(httpCacheDirectory, cacheSize);
    private val DEFAULT_TIMEOUT = 10L

    private lateinit var client: OkHttpClient


    //Services
    private var locationService: LocationService? = null
    private var userService: UserService? = null
    private var commonService: CommonService? = null
    private var flightService: FlightService? = null

    /**
     * 初始化客户端
     */
    fun init(applicationContext: Context) {
        client = OkHttpClient.Builder()
                .connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .addInterceptor(ABARequestInterceptor())
                .readTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .writeTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS)
                .build()
    }


    /**
     * Service 构造器
     */
    private fun <T> createService(serviceClass: Class<T>, vararg factory: Converter.Factory,
                                  baseUrl: String = ApiInfo.BASE_URL): T {
        val build = Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(client)
        factory.forEach {
            build.addConverterFactory(it)
        }
        val retrofit = build
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build()
        return retrofit.create(serviceClass)
    }


    private fun getLocationService(vararg factory: Converter.Factory): LocationService {
        if (locationService == null)
            locationService = createService(LocationService::class.java, *factory,
                    baseUrl = ApiInfo.BAIDU_LOCATION_BASE_URL)
        return locationService!!
    }


    private fun getUserService(vararg factory: Converter.Factory): UserService {
        if (userService == null)
            userService = createService(UserService::class.java, *factory)
        return userService!!
    }

    private fun getCommonService(vararg factory: Converter.Factory): CommonService {
        if (commonService == null)
            commonService = createService(CommonService::class.java, *factory)
        return commonService!!
    }

    private fun getFlightService(vararg factory: Converter.Factory): FlightService {
        if (flightService == null)
            flightService = createService(FlightService::class.java, *factory)
        return flightService!!
    }

    /**
     * 全局的网络请求拦截器
     */
    class ABARequestInterceptor : Interceptor {
        override fun intercept(chain: Interceptor.Chain): Response {
            val request = chain.request()
            //begin deal some operation before request

            val originResponse = chain.proceed(request)
            //begin do something after receive response

            val responseBody = originResponse.body()
            val source = responseBody!!.source()
            source.request(java.lang.Long.MAX_VALUE) // Buffer the entire body.
            val buffer = source.buffer()
            var charset: Charset? = UTF8
            val contentType = responseBody.contentType()
            if (contentType != null) {
                charset = contentType.charset(UTF8)
                val responseString = buffer.clone().readString(charset!!)
                Logger.i(responseString)
                Logger.i(request.url().toString())
                Logger.i("token: ${request.header("authorization")}")
            }
            return originResponse
        }

    }


    ////////////////////////////////////////////////////////////
    ///////// 地理信息相关API
    ////////////////////////////////////////////////////////////
    fun getLocationInfo(latitude: Double, longitude: Double)
            : Observable<LocationInfo> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getLocationService(GsonConverterFactory.create())
                            .getLocationInfo("$latitude,$longitude")
                }
    }


    ////////////////////////////////////////////////////////////
    ////////// 用户管理相关API
    ////////////////////////////////////////////////////////////

    /**
     * 登陆
     */
    fun login(username: String, password: String): Observable<ResponseBody<LoginResponseData>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getUserService(GsonConverterFactory.create())
                            .login(username, password)
                }
    }

    /**
     * 注册
     */
    fun register(username: String, password: String, email: String = "", phone: String = "")
            : Observable<ResponseBody<RegisterResponseData>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getUserService(GsonConverterFactory.create())
                            .register(username, password, email, username, phone)
                }
    }

    /**
     * 更新失效的token
     */
    fun updateToken(): Observable<ResponseBody<UpdateTokenResponseData>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getUserService(GsonConverterFactory.create())
                            .updateToken(ApiInfo.BASE_TOKEN_PREFIX + ABAApi.authorizationToken)
                }
    }

    /**
     * 获取乘机人信息
     */
    fun getPassengerContacts(): Observable<ResponseBody<List<Passenger>>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getUserService(GsonConverterFactory.create())
                            .getPassengerContacts(ApiInfo.BASE_TOKEN_PREFIX + ABAApi.authorizationToken)
                }
    }

    /**
     * 添加乘机人
     */
    fun addPassengerContact(
            name: String,
            certificateType: Int,
            certificateValue: String,
            isAdult: Boolean,
            phone: String = "",
            email: String = ""
    ): Observable<ResponseBody<Passenger>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getUserService(GsonConverterFactory.create())
                            .addPassengerContact(ApiInfo.BASE_TOKEN_PREFIX + ABAApi.authorizationToken,
                                    name, certificateType, certificateValue, phone, email, isAdult)
                }
    }


    /**
     * 更新乘机人
     */
    fun updatePassengerContact(
            id: String,
            name: String,
            certificateType: Int,
            certificateValue: String,
            isAdult: Boolean,
            phone: String = "",
            email: String = ""
    ): Observable<ResponseBody<Passenger>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getUserService(GsonConverterFactory.create())
                            .updatePassengerContact(ApiInfo.BASE_TOKEN_PREFIX + ABAApi.authorizationToken,
                                    id, name, certificateType, certificateValue, phone, email, isAdult)
                }
    }

    /**
     * 删除乘机人
     */
    fun deletePassengerContact(
            id: String
    ): Observable<ResponseBody<EmptyResponseData>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getUserService(GsonConverterFactory.create())
                            .deletePassengerContact(ApiInfo.BASE_TOKEN_PREFIX + ABAApi.authorizationToken,
                                    id)
                }
    }


    /**
     * 获取订单列表
     */
    fun getOrders(page: Int, size: Int): Observable<ResponseBody<List<GetOrdersResponseData>>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getUserService(GsonConverterFactory.create())
                            .getOrders(ApiInfo.BASE_TOKEN_PREFIX + ABAApi.authorizationToken,
                                    page, size)
                }
    }

    /**
     * 删除订单
     */
    fun deleteOrder(orderId: String): Observable<ResponseBody<EmptyResponseData>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getUserService(GsonConverterFactory.create())
                            .deleteOrder(ApiInfo.BASE_TOKEN_PREFIX + ABAApi.authorizationToken,
                                    orderId)
                }
    }

    /**
     * 获取用户信息
     */
    fun getUserInfo(): Observable<ResponseBody<User>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getUserService(GsonConverterFactory.create())
                            .getUserInfo(ApiInfo.BASE_TOKEN_PREFIX + ABAApi.authorizationToken)
                }
    }

    ///////////////////////////////////////////////////////////////////////
    ///////// 航班相关接口
    //////////////////////////////////////////////////////////////////////

    /**
     * 检索指定日期，起始城市到到达城市之间可用的航班
     */
    fun searchAvailableFlight(fromCityCode: String, toCityCode: String, date: Long, page: Int = 1,
                              size: Int = 10): Observable<ResponseBody<List<SearchAvaliableFlightResponseData>>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getFlightService(GsonConverterFactory.create())
                            .searchAvailableFlight(fromCityCode, toCityCode, date, page, size)
                }
    }

    /**
     * 生成订单
     */
    fun generateOrder(ticketId: String, passengers: ArrayList<String>, name: String,
                      phone: String, email: String = ""): Observable<ResponseBody<EmptyResponseData>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getFlightService(GsonConverterFactory.create())
                            .generateOrder(ApiInfo.BASE_TOKEN_PREFIX + ABAApi.authorizationToken,
                                    ticketId, passengers, name, phone, email)
                }
    }

    /**
     * 退票
     */
    fun returnTicket(orderId: String): Observable<ResponseBody<EmptyResponseData>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getFlightService(GsonConverterFactory.create())
                            .returnTicket(ApiInfo.BASE_TOKEN_PREFIX + ABAApi.authorizationToken,
                                    orderId)
                }
    }


    ///////////////////////////////////////////////////////////////////////
    /////////   通用API接口
    ///////////////////////////////////////////////////////////////////////

    /**
     * 获取城市列表
     */
    fun getCities(): Observable<ResponseBody<List<City>>> {
        return Observable.just(1)
                .flatMap {
                    return@flatMap getCommonService(GsonConverterFactory.create())
                            .getCities()
                }
    }
}