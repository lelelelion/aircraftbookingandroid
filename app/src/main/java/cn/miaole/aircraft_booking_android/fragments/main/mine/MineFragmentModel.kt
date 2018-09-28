package cn.miaole.aircraft_booking_android.fragments.main.mine

import cn.miaole.aircraft_booking_android.model.ABAApi
import com.orhanobut.logger.Logger

class MineFragmentModel(val mPresenter: MineFragmentPresenter) : MineFragmentContract.Model {
    override fun logout() {
        ABAApi.updateAuthorizationToken("")
        ABAApi.updateIsLogin(false)
        ABAApi.updateUserInfo(null)
        mPresenter.logoutSuccess()
    }
}