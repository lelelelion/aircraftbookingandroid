package cn.miaole.aircraft_booking_android.model.internet.services

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.api.ApiInfo
import cn.miaole.aircraft_booking_android.model.internet.data.ResponseBody
import cn.miaole.aircraft_booking_android.model.internet.data.SearchAvaliableFlightResponseData
import io.reactivex.Observable
import retrofit2.http.Field
import retrofit2.http.GET
import retrofit2.http.Query

interface FlightService {

    /**
     * 检索指定日期，起始城市到到达城市之间可用的航班
     */
    @GET(ApiInfo.SEARCH_AVALIABLE_FLIGHT_URL)
    fun searchAvailableFlight(
            @Query(ApiInfo.SEARCH_AVALIABLE_FLIGHT_PARAM_FROM_CITY_CODE) fromCityCode: String,
            @Query(ApiInfo.SEARCH_AVALIABLE_FLIGHT_PARAM_TO_CITY_CODE) toCityCode: String,
            @Query(ApiInfo.SEARCH_AVALIABLE_FLIGHT_PARAM_DATE) date: Long,
            @Query(ApiInfo.SEARCH_AVALIABLE_FLIGHT_PARAM_PAGE) page: Int,
            @Query(ApiInfo.SEARCH_AVALIABLE_FLIGHT_PARAM_SIZE) size: Int
    ): Observable<ResponseBody<List<SearchAvaliableFlightResponseData>>>
}