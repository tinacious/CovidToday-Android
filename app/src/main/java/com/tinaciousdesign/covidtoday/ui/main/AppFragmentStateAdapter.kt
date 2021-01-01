package com.tinaciousdesign.covidtoday.ui.main

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import com.tinaciousdesign.covidtoday.data.getTabs


class AppFragmentStateAdapter(activity: FragmentActivity): FragmentStateAdapter(activity) {
    private var mActivity: FragmentActivity = activity

    override fun getItemCount(): Int = getTabs(mActivity).size

    override fun createFragment(position: Int): Fragment = PageFragment()
}
