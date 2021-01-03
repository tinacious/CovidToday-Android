package com.tinaciousdesign.covidtoday

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.viewpager2.widget.ViewPager2
import com.google.android.material.tabs.TabLayout
import com.google.android.material.tabs.TabLayoutMediator
import com.tinaciousdesign.covidtoday.data.getTabs
import com.tinaciousdesign.covidtoday.ui.main.AppFragmentStateAdapter

class MainActivity : AppCompatActivity() {
    private lateinit var mViewPager: ViewPager2

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val toolbar: Toolbar = findViewById(R.id.appBar1)
        setSupportActionBar(toolbar)

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
    }


    override fun onBackPressed() {
        if (mViewPager.currentItem == 0) return super.onBackPressed()
        mViewPager.currentItem = mViewPager.currentItem - 1
    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater: MenuInflater = menuInflater
        inflater.inflate(R.menu.options_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.menu_about -> handleAboutClicked()
            else -> return super.onOptionsItemSelected(item)
        }
        return true
    }

    private fun handleAboutClicked() {
        AlertDialog.Builder(this)
            .setTitle(R.string.info_open_title)
            .setMessage(R.string.info_open_description)
            .setPositiveButton(android.R.string.ok) { _, _ ->
                val webIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://github.com/tinacious/CovidToday-Android"))
                startActivity(webIntent)
            }
            .setNegativeButton(android.R.string.cancel, null)
            .show()
    }

    companion object {
        private const val TAG = "MainActivity"
    }
}
