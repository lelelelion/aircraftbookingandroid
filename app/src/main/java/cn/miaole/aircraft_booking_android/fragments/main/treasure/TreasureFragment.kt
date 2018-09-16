package cn.miaole.aircraft_booking_android.fragments.main.treasure

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.fragment.MVPBaseFragment
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*

class TreasureFragment : MVPBaseFragment<TreasureFragmentPresenter>(), TreasureFragmentContract.View {

    companion object {
        fun newInstance(): TreasureFragment {
            val args = Bundle()
            val fragment = TreasureFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreatePresenter(): TreasureFragmentPresenter =
            TreasureFragmentPresenter(this)

    override fun getRes(): Int = R.layout.fragment_main_treasure

    override fun initView() {
        easyBar.init(mode = EasyBar.Mode.NONE, titleRes = R.string.treasure)
    }

    override fun initialLoadData() {
    }

}