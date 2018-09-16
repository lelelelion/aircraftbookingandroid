package cn.miaole.aircraft_booking_android.activitys.main

import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter

class MainAdapter(fm: FragmentManager, val fragments: MutableList<Fragment>)
    : FragmentPagerAdapter(fm) {
    override fun getCount(): Int = fragments.size

    override fun getItem(p0: Int): Fragment = fragments[p0]

}