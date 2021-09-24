package com.myviewapplication.databinding

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter

/**
 * Created by Jeff on 2021/9/22
 */
class TestVPAdapter(
    fragmentManager: FragmentManager,
    private val fragmentList: ArrayList<Fragment>,
    private val titleArray: Array<String>
) : FragmentPagerAdapter(fragmentManager) {

    override fun getItem(position: Int): Fragment {
        return fragmentList[position]
    }

    override fun getCount(): Int {
        return fragmentList.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return titleArray[position]
    }

}