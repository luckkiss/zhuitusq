package com.ztsq.chen.zhutusq.ui.fragment

import android.support.design.widget.TabLayout
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.app.FragmentPagerAdapter
import com.ztsq.chen.zhutusq.R
import com.ztsq.chen.zhutusq.ui.commom.CommonFragment
import com.ztsq.chen.zhutusq.ui.fragment.sub.GirlFragment
import com.ztsq.chen.zhutusq.ui.fragment.sub.MyPickFragment
import kotlinx.android.synthetic.main.fragment_main.*

class MainFragment:CommonFragment(),TabLayout.OnTabSelectedListener {

    class MainPagerAdapter(fm: FragmentManager?) : FragmentPagerAdapter(fm) {
        private val titleList: Array<String>? = arrayOf("图册", "社区")

        override fun getItem(position: Int): Fragment {
            when (position) {
                0 -> return GirlFragment()
                1 -> return GirlFragment()
            }
            return GirlFragment()
        }

        override fun getCount(): Int {
            return titleList?.size!!
        }

        override fun getPageTitle(position: Int): CharSequence {
            return titleList?.get(position)!!
        }
    }

    override fun getLayoutResources(): Int {
        return R.layout.fragment_main
    }

    override fun initView() {
        viewPager.adapter = MainPagerAdapter(activity?.supportFragmentManager)
        viewPager.offscreenPageLimit = 1
        tabLayout.addOnTabSelectedListener(this)
        tabLayout.setupWithViewPager(viewPager)
    }

    override fun onTabReselected(tab: TabLayout.Tab?) {

    }

    override fun onTabUnselected(tab: TabLayout.Tab?) {

    }

    override fun onTabSelected(tab: TabLayout.Tab?) {
        when (tab) {
            tabLayout.getTabAt(0) -> viewPager.currentItem = 0
            tabLayout.getTabAt(1) -> viewPager.currentItem = 1
        }
    }

    override fun loadData() {
    }
}