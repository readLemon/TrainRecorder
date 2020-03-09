package com.example.train.view.fragment.adapter

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.example.train.view.fragment.AddPersonalChildTeamFragment
import com.example.train.view.fragment.AddTeamChildTeamFragment

/**
 * Created by chenyang
 * on 20-2-29
 */
class PageAdapter(fm: FragmentManager, val cnt: Int, val fragmentHashMap: HashMap<Int, Fragment> = HashMap()) : FragmentPagerAdapter(fm, cnt) {

    override fun getItem(position: Int): Fragment {
        return getFragment(position)
    }


    private fun getFragment(pos: Int): Fragment {
        var currentFragment = fragmentHashMap.get(pos)

        if (currentFragment == null) {
            when (pos) {
                0 -> {
                    currentFragment =
                        AddTeamChildTeamFragment()
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
        when (position) {
            0 -> return "个人记录"

            else -> return "团队记录"
        }
    }

}