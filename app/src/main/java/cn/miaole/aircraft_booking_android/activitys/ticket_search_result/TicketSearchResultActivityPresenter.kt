package cn.miaole.aircraft_booking_android.activitys.ticket_search_result

import cn.miaole.aircraft_booking_android.model.internet.data.SearchAvaliableFlightResponseData

class TicketSearchResultActivityPresenter(ticketSearchResultActivity: TicketSearchResultActivity)
    : TicketSearchResultActivityContact.Presenter() {
    override fun searchAvailableFlight(fromCityCode: String, toCityCode: String, date: Long,
                                       page: Int, size: Int, isRefresh: Boolean) {
        mModel.searchAvailableFlight(fromCityCode, toCityCode, date, page, size, isRefresh)
    }

    override fun searchAvailableFlightSuccess(result: List<SearchAvaliableFlightResponseData>,
                                              isRefresh: Boolean) {
        mView.searchAvailableFlightSuccess(result, isRefresh)
    }

    init {
        mView = ticketSearchResultActivity
        mModel = TicketSearchResultActivityModel(this)
    }
}