package cn.miaole.aircraft_booking_android.activitys.booking

import cn.miaole.aircraft_booking_android.model.internet.data.Passenger

class BookingActivityPresenter(bookingActivity: BookingActivity)
    : BookingActivityContract.Presenter() {
    override fun generateOrderSuccess() {
        mView.generateOrderSuccess()
    }

    override fun generateOrderFail() {
        mView.generateOrderFail()
    }

    override fun generateOrder(ticketId: String, passengers: ArrayList<String>, name: String,
                               phone: String, email: String) {
        mModel.generateOrder(ticketId, passengers, name, phone, email)
    }

    override fun loadPassengers() {
        mModel.loadPassengers()
    }

    override fun loadPassengersSuccess(t: List<Passenger>) {
        mView.loadPassengersSuccess(t)
    }

    init {
        mView = bookingActivity
        mModel = BookingActivityModel(this)
    }
}