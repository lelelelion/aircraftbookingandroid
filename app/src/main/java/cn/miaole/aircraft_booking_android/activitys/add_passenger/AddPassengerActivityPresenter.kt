package cn.miaole.aircraft_booking_android.activitys.add_passenger

import cn.miaole.aircraft_booking_android.model.internet.data.Passenger

class AddPassengerActivityPresenter(addPassengerActivity: AddPassengerActivity)
    : AddPassengerActivityContact.Presenter() {
    override fun updatePassengerSuccess(passenger: Passenger) {
        mView.updatePassengerSuccess(passenger)
    }

    override fun updatePassengerContact(id: String, name: String, certificateType: Int, certificateValue: String,
                                        isAdult: Boolean, phone: String, email: String) {
        mModel.updatePassengerContact(id, name, certificateType, certificateValue, isAdult, phone, email)
    }

    override fun addPassengerContact(name: String, certificateType: Int, certificateValue: String,
                                     isAdult: Boolean, phone: String, email: String) {
        mModel.addPassengerContact(name, certificateType, certificateValue, isAdult, phone, email)
    }

    override fun addPassengerSuccess(passenger: Passenger) {
        mView.addPassengerSuccess(passenger)
    }

    init {
        mView = addPassengerActivity
        mModel = AddPassengerActivityModel(this)
    }
}