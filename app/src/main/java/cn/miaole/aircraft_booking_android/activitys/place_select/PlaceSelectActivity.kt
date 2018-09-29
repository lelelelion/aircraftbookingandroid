package cn.miaole.aircraft_booking_android.activitys.place_select

import android.content.Intent
import android.os.Bundle
import android.view.View
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.activitys.place_select.item.CityBarItem
import cn.miaole.aircraft_booking_android.activitys.place_select.item.CityItem
import cn.miaole.aircraft_booking_android.model.internet.data.City
import cn.miaole.aircraft_booking_android.utils.easyToJson
import cn.miaole.aircraft_booking_android.views.easy_refresh.CustomLinerLayoutManager
import com.chad.library.adapter.base.entity.MultiItemEntity
import com.j.ming.easybar2.EasyBar
import com.j.ming.easybar2.init
import com.lwkandroid.widget.indexbar.IndexBar
import kotlinx.android.synthetic.main.activity_place_select.*
import kotlinx.android.synthetic.main.bar_item.*
import org.jetbrains.anko.doAsync
import org.jetbrains.anko.uiThread

class PlaceSelectActivity : MVPBaseActivity<PlaceSelectActivityPresenter>(),
        PlaceSelectActivityContract.View {

    companion object {
        const val RESULT_CITY = "RESULT_CITY"
    }

    private lateinit var adapter: CityAdapter
    private val indexHash = HashMap<String, Int>()

    override fun getCitiesSuccess(list: List<City>) {
        doAsync {
            val set = HashSet<String>()
            val result = mutableListOf<MultiItemEntity>()
            list.sortedBy {
                return@sortedBy it.code
            }.forEachIndexed { index, city ->
                val key = city.code[0].toUpperCase().toString()
                if (set.add(key)) {
                    indexHash[key] = result.size
                    result.add(CityBarItem(key))
                }
                result.add(CityItem(city))
            }
            uiThread {
                indexBar.setTextArray(set.sorted().toTypedArray())
                adapter.addData(result)
            }
        }
    }

    override fun onCretePresenter(): PlaceSelectActivityPresenter = PlaceSelectActivityPresenter(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_place_select)
        initView()
    }

    private fun initView() {
        easyBar.init(mode = EasyBar.Mode.ICON_, titleRes = R.string.select_city, leftCallback = {
            onBackPressed()
        })

        //init recyclerView
        recyclerView.layoutManager = CustomLinerLayoutManager(this)
        adapter = CityAdapter(mutableListOf())
        adapter.bindToRecyclerView(recyclerView)

        adapter.setOnItemClickListener { adapter, view, position ->
            (adapter as CityAdapter).getItem(position)?.let {
                if (it.itemType == CityAdapter.ITEM_TYPE_CITY) {
                    Intent().apply {
                        putExtra(RESULT_CITY, (it as CityItem).city.easyToJson())
                        this@PlaceSelectActivity.setResult(0, this)
                        this@PlaceSelectActivity.finish()
                    }
                }
            }
        }
        indexBar.setOnIndexLetterChangedListener(object : IndexBar.OnIndexLetterChangedListener {
            override fun onTouched(touched: Boolean) {
                if (touched)
                    tvIndicator.visibility = View.VISIBLE
                else
                    tvIndicator.visibility = View.GONE
            }

            override fun onLetterChanged(indexChar: CharSequence?, index: Int, y: Float) {
                indexChar?.let {
                    tvIndicator.text = indexChar
                    indexHash[indexChar.toString()]?.let { it1 ->
                        recyclerView.smoothScrollToPosition(it1)
                    }
                }

            }
        })

        mPresenter.getCities()
    }
}
