package cn.miaole.aircraft_booking_android.model.internet.services

import cn.miaole.aircraft_booking_android.model.internet.api.ApiInfo
import cn.miaole.aircraft_booking_android.model.internet.data.LocationInfo
import io.reactivex.Observable
import retrofit2.http.GET
import retrofit2.http.Query

interface LocationService {

    /**
     * 传入当前的经纬度，返回当前的详细位置信息
     */
    @GET(ApiInfo.BAIDU_LOCATION_GET_LOCATION_URL)
    fun getLocationInfo(
            @Query(ApiInfo.BAIDU_LOCATION_GET_LOCATION_LOCATION) location: String,
            @Query(ApiInfo.BAIDU_LOCATION_GET_LOCATION_OUTPUT) output: String = "json",
            @Query(ApiInfo.BAIDU_LOCATION_GET_LOCATION_KEY) key: String
            = ApiInfo.BAIDU_LOCATION_GET_LOCATION_KEY_VALUE)
    : Observable<LocationInfo>

}