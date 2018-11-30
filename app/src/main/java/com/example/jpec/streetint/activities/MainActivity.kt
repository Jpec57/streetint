package com.example.jpec.streetint.activities

import android.os.Bundle
import com.example.jpec.streetint.R
import com.example.jpec.streetint.fragments.main_activity.MainFragment
import com.example.jpec.streetint.fragments.main_activity.QuickChronoFragment

class MainActivity : androidx.fragment.app.FragmentActivity() {
    val NUM_PAGES = 2
    private lateinit var mPager: androidx.viewpager.widget.ViewPager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        // Instantiate a ViewPager and a PagerAdapter.
        mPager = findViewById(R.id.pager)

        // The pager adapter, which provides the pages to the view pager widget.
        val pagerAdapter = ScreenSlidePagerAdapter(supportFragmentManager)
        mPager.adapter = pagerAdapter
    }

    override fun onBackPressed() {
        if (mPager.currentItem == 0) {
            // If the user is currently looking at the first step, allow the system to handle the
            // Back button. This calls finish() on this activity and pops the back stack.
            super.onBackPressed()
        } else {
            // Otherwise, select the previous step.
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
