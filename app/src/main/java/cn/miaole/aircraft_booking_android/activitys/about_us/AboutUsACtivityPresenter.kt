package cn.miaole.aircraft_booking_android.activitys.about_us

class AboutUsACtivityPresenter(aboutUs: AboutUsActivity)
    : AboutUsActivityContract.Presenter() {
    init {
        mView = aboutUs
        mModel = AboutUsActivityModel(this)
    }
}