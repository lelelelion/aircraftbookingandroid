package cn.miaole.aircraft_booking_android.activitys.ticket_search_result

import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseModel
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BasePresenter
import cn.miaole.aircraft_booking_android.activitys.base.mvp.BaseView
import cn.miaole.aircraft_booking_android.model.internet.data.SearchAvaliableFlightResponseData

interface TicketSearchResultActivityContact {
    interface View : BaseView {
        fun searchAvailableFlightSuccess(result: List<SearchAvaliableFlightResponseData>, isRefresh: Boolean)
    }

    interface Model : BaseModel {
        fun searchAvailableFlight(fromCityCode: String, toCityCode: String, date: Long, page: Int = 1,
                                  size: Int = 10, isRefresh: Boolean)
    }

    abstract class Presenter : BasePresenter<View, Model>() {
        abstract fun searchAvailableFlight(fromCityCode: String, toCityCode: String, date: Long, page: Int = 1,
                                           size: Int = 10, isRefresh: Boolean)
        abstract fun searchAvailableFlightSuccess(result: List<SearchAvaliableFlightResponseData>, isRefresh: Boolean)
    }
}