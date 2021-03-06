package cn.miaole.aircraft_booking_android.activitys.main

import android.location.Location
import android.net.Uri
import android.os.Bundle
import android.support.v4.app.Fragment
import android.support.v4.view.ViewPager
import cn.miaole.aircraft_booking_android.R
import cn.miaole.aircraft_booking_android.activitys.base.ABAFragment
import cn.miaole.aircraft_booking_android.activitys.base.activity.MVPBaseActivity
import cn.miaole.aircraft_booking_android.extensions.scaleXY
import cn.miaole.aircraft_booking_android.extensions.toast
import cn.miaole.aircraft_booking_android.fragments.main.index.IndexFragment
import cn.miaole.aircraft_booking_android.fragments.main.mine.MineFragment
import cn.miaole.aircraft_booking_android.fragments.main.treasure.TreasureFragment
import cn.miaole.aircraft_booking_android.model.internet.api.APIManager
import cn.miaole.aircraft_booking_android.model.internet.data.LocationInfo
import cn.miaole.aircraft_booking_android.model.internet.rx.RxObserver
import cn.miaole.aircraft_booking_android.utils.LocationUtil
import cn.miaole.aircraft_booking_android.utils.RxSchedulersHelper
import cn.miaole.aircraft_booking_android.utils.easyToJson
import cn.miaole.aircraft_booking_android.views.TinImageView
import com.github.dfqin.grantor.PermissionListener
import com.github.dfqin.grantor.PermissionsUtil
import com.orhanobut.logger.Logger
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_main_bottom.*

class MainActivity : MVPBaseActivity<MainActivityPresenter>(), MainActivityContract.View,
        ABAFragment.OnFragmentInteractionListener {
    override fun onFragmentInteraction(uri: Uri?) {

    }

    override fun onCretePresenter(): MainActivityPresenter = MainActivityPresenter(this)

    companion object {
        const val BOTTOM_INDEX = 0
        const val BOTTOM_TREASURE = 1
        const val BOTTOM_MINE = 2
    }

    private lateinit var adapter: MainAdapter
    private val fragments = mutableListOf<Fragment>()
    private val viewArr = mutableListOf<TinImageView>()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initView()

        //init LocationUtil
        LocationUtil.bind(this)
        requestLocationPermission()
    }


    private fun initLocationListener(){
        LocationUtil.beginListen()
        LocationUtil.addOnLocationChangeListener(object: LocationUtil.OnLocationChangeListener{
            override fun onGpsLocationChange(location: Location) {
                Logger.i("gps location change", location.easyToJson())
            }

            override fun onNetworkLocationChange(location: Location) {
                Logger.i("network location change: (${location.latitude}, ${location.longitude})")
                APIManager.getLocationInfo(location.latitude, location.longitude)
                        .compose(RxSchedulersHelper.io_main())
                        .subscribe(object : RxObserver<LocationInfo>(mPresenter){
                            override fun _onNext(t: LocationInfo) {
                                Logger.i(t.easyToJson())
                                toast(t.result.formatted_address)
                            }
                        })

            }

        })

    }
    /**
     * 动态请求获取地理位置的权限
     */
    private fun requestLocationPermission() {
        PermissionsUtil.requestPermission(this, object : PermissionListener {
            override fun permissionDenied(permission: Array<out String>) {
                toast("Execuse me? 定位权限都不给，能不能愉快的玩耍了...手机将在3妙后爆炸")
                toast("3")
                toast("2")
                toast("1")
                toast("boom")
                toast("骗你的>''<，下次记得给我权限")
            }

            override fun permissionGranted(permission: Array<out String>) {
                layout.post {
                    //界面渲染完成后执行
                    LocationUtil.ensureLocationServerOpen()
                    initLocationListener()
                }
            }
        }, android.Manifest.permission.ACCESS_FINE_LOCATION, android.Manifest.permission.ACCESS_COARSE_LOCATION)

    }

    private fun initView() {
        fragments.add(IndexFragment.newInstance())
        fragments.add(TreasureFragment.newInstance())
        fragments.add(MineFragment.newInstance())

        adapter = MainAdapter(supportFragmentManager, fragments)

        viewPager.adapter = adapter

        viewPager.addOnPageChangeListener(object : ViewPager.OnPageChangeListener {
            override fun onPageScrollStateChanged(p0: Int) {
            }

            override fun onPageScrolled(p0: Int, p1: Float, p2: Int) {
            }

            override fun onPageSelected(p0: Int) {
                // 滑动结束时切换底部菜单栏
                select(p0)
            }
        })

        imgIndex.setOnClickListener {
            select(BOTTOM_INDEX)
        }
        imgTreasure.setOnClickListener {
            select(BOTTOM_TREASURE)
        }
        imgMine.setOnClickListener {
            select(BOTTOM_MINE)
        }

        viewArr.add(imgIndex)
        viewArr.add(imgTreasure)
        viewArr.add(imgMine)

        //初始时显示首页
        viewPager.currentItem = 0

        select(BOTTOM_INDEX)
    }


    /**
     * 切换到某一个模块
     */
    private fun select(position: Int) {
        doToSelectedView(position) { view ->
            view.updateDrawableTinColor(R.color.colorPrimary)
            view.scaleXY(1f, 1.2f, 1.1f)
        }

        doToNotSelectedViews(position) { view ->
            view.updateDrawableTinColor(R.color.grey)
            view.scaleXY(1f)
        }
        viewPager.currentItem = position
    }

    /**
     * 对选中的菜单项执行一个操作
     */
    private fun doToSelectedView(position: Int, cb: (view: TinImageView) -> Unit) {
        viewArr[position].apply(cb)
    }

    /**
     * 对未选中的菜单项执行一个操作
     */
    private fun doToNotSelectedViews(selectedPosition: Int, op: (view: TinImageView) -> Unit) {
        viewArr.filterIndexed { index, _ ->
            return@filterIndexed index != selectedPosition
        }.forEach {
            op(it)
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>, grantResults: IntArray) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}
