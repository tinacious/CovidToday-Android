package com.tinaciousdesign.covidtoday

import android.os.Bundle
import androidx.fragment.app.FragmentActivity
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tinaciousdesign.covidtoday.data.getTabs
import com.tinaciousdesign.covidtoday.ui.main.AppFragmentStateAdapter

class MainActivity : FragmentActivity() {
    private lateinit var mViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // View pager
        mViewPager = findViewById(R.id.pager)
        val adapter = AppFragmentStateAdapter(this)
        mViewPager.adapter = adapter

        // Tabs
        val tabTitles: List<String> = getTabs(this)
        val tabLayout: TabLayout = findViewById(R.id.tabs)
        TabLayoutMediator(tabLayout, mViewPager) { tab, position ->
            tab.text = tabTitles[position]
        }.attach()

//        ViewModelProvider(this)
//            .get(MainViewModel::class.java)
//            .countries.observe(this, {
//            })
    }


    override fun onBackPressed() {
        if (mViewPager.currentItem == 0) return super.onBackPressed()
        mViewPager.currentItem = mViewPager.currentItem - 1
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
