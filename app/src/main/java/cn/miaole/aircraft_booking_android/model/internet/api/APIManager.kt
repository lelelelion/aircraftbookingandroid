package cn.miaole.aircraft_booking_android.model.internet.api

import android.content.Context
import cn.miaole.aircraft_booking_android.model.internet.data.LocationInfo
import cn.miaole.aircraft_booking_android.model.internet.data.ResponseBody
import cn.miaole.aircraft_booking_android.model.internet.services.LocationService
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


    fun getLocationService(vararg factory: Converter.Factory): LocationService {
        if (locationService == null)
            locationService = createService(LocationService::class.java, *factory,
                    baseUrl = ApiInfo.BAIDU_LOCATION_BASE_URL)
        return locationService!!
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
                Logger.i("token: ${request.header("token")}")
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
}