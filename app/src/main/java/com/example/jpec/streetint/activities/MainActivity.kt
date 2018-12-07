package com.example.jpec.streetint.activities

import android.os.Bundle
import com.example.jpec.streetint.R
import com.example.jpec.streetint.fragments.mainActivity.MainFragment
import com.example.jpec.streetint.fragments.mainActivity.QuickChronoFragment

class MainActivity : androidx.fragment.app.FragmentActivity() {
    val NUM_PAGES = 2
    private lateinit var mPager: androidx.viewpager.widget.ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mPager = findViewById(R.id.pager)
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            super.onBackPressed()
        } else {
            mPager.currentItem = mPager.currentItem - 1
        }
    }

    private inner class ScreenSlidePagerAdapter(fm: androidx.fragment.app.FragmentManager) : androidx.fragment.app.FragmentStatePagerAdapter(fm) {
        override fun getCount(): Int = NUM_PAGES

        override fun getItem(position: Int): androidx.fragment.app.Fragment =
            when (position)
            {
                0 -> MainFragment()
                else -> QuickChronoFragment()

            }
    }

}
