package com.example.train.view.fragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.train.view.fragment.AddPersonalChildTeamFragment

/**
 * Created by chenyang
 * on 20-2-29
 */
class PageAdapter(val fm: FragmentManager, val cnt: Int) : FragmentPagerAdapter(fm, cnt) {

    private val fragmentHashMap = HashMap<Int, Fragment>()
    override fun getItem(position: Int): Fragment {
        return getFragment(position)
    }


    private fun getFragment(pos: Int): Fragment {
        var currentFragment = fragmentHashMap.get(pos)

        if (currentFragment == null) {
            when (pos) {
                0 -> {
                    currentFragment = AddTeamChildTeamFragment()
                    fragmentHashMap.put(0, currentFragment)
                }

                1 -> {
                    currentFragment = AddPersonalChildTeamFragment()
                    fragmentHashMap.put(1, currentFragment)
                }

            }
        }

        return currentFragment as Fragment

    }

    override fun getCount() = cnt

    override fun getPageTitle(position: Int): CharSequence? {
        when(position) {
            0 -> return "team"

            else -> return "person"
        }
    }

}