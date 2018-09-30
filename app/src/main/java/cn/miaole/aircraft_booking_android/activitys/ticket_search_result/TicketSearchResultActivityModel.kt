package cn.miaole.aircraft_booking_android.activitys.ticket_search_result

import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.SearchAvaliableFlightResponseData
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.model.internet.rx.RxResultHelper
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper

class TicketSearchResultActivityModel(val mPresenter: TicketSearchResultActivityPresenter)
    : TicketSearchResultActivityContact.Model {
    override fun searchAvailableFlight(fromCityCode: String, toCityCode: String,
                                       date: Long, page: Int, size: Int, isRefresh: Boolean) {
        APIManager
                .searchAvailableFlight(fromCityCode, toCityCode, date, page, size)
                .compose(RxSchedulersHelper.io_main())
                .compose(RxResultHelper.handleResult())
                .subscribe(object : RxObserver<List<SearchAvaliableFlightResponseData>>(mPresenter) {
                    override fun _onNext(t: List<SearchAvaliableFlightResponseData>) {
                        mPresenter.searchAvailableFlightSuccess(t, isRefresh)
                    }
                })
    }
}