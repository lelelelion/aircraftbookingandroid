package cn.miaole.aircraft_booking_android.fragments.main.index

import android.os.Bundle
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.fragment.MVPBaseFragment
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import kotlinx.android.synthetic.main.bar_item.*

class IndexFragment : MVPBaseFragment<IndexFragmentPresenter>(), IndexFragmentContract.View {

    companion object {
        fun newInstance(): IndexFragment {
            val args = Bundle()
            val fragment = IndexFragment()
            fragment.arguments = args
            return fragment
        }
    }

    override fun onCreatePresenter(): IndexFragmentPresenter =
            IndexFragmentPresenter(this)

    override fun getRes(): Int = R.layout.fragment_main_index

    override fun initView() {
        easyBar.init(mode = EasyBar.Mode.NONE, titleRes = R.string.index)
    }

    override fun initialLoadData() {
    }

}