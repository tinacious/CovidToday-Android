package com.tinaciousdesign.covidtoday

import android.os.Bundle
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.adapter.FragmentStateAdapter
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tinaciousdesign.covidtoday.data.getTabs
import com.tinaciousdesign.covidtoday.ui.main.AppFragmentStateAdapter
import com.tinaciousdesign.covidtoday.ui.main.PageFragment

class MainActivity : FragmentActivity() {
    private lateinit var mViewPager: ViewPager2
    private lateinit var mTabTexts: List<String>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View pager
        mViewPager = findViewById(R.id.pager)
        val adapter = AppFragmentStateAdapter(this)
        mViewPager.adapter = adapter

        // Tabs
        mTabTexts = getTabs(this)
        val tabLayout: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabLayout, mViewPager) { tab, position ->
            tab.text = mTabTexts[position]
        }.attach()
    }

    override fun onBackPressed() {
        if (mViewPager.currentItem == 0) return super.onBackPressed()
        mViewPager.currentItem = mViewPager.currentItem - 1
    }
}
