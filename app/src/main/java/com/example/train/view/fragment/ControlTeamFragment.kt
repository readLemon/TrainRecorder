package com.example.train.view.fragment

import android.view.View
import com.example.train.R
import com.example.train.view.fragment.adapter.PageAdapter
import kotlinx.android.synthetic.main.fragment_control_team.*

/**
 * Created by chenyang
 * on 20-2-27
 */
class ControlTeamFragment : BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_control_team


    override fun initial(view: View) {
        super.initial(view)
        vp_control_team_fragmrnt.adapter = PageAdapter(childFragmentManager, 2)
        tl_control_team_fragment.setupWithViewPager(vp_control_team_fragmrnt)
    }
}