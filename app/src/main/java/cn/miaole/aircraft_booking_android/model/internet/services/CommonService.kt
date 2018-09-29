package cn.miaole.aircraft_booking_android.model.internet.services

import cn.miaole.aircraft_booking_android.model.internet.api.ApiInfo
import cn.miaole.aircraft_booking_android.model.internet.data.City
import cn.miaole.aircraft_booking_android.model.internet.data.ResponseBody
import io.reactivex.Observable
import retrofit2.http.GET

interface CommonService{

    @GET(ApiInfo.GET_CITIES)
    fun getCities(): Observable<ResponseBody<List<City>>>
}