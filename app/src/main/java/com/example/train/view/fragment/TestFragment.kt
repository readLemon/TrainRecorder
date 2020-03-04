package com.example.train.view.fragment

import android.view.View
import com.example.train.R
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * Created by chenyang
 * on 20-3-4
 */
class TestFragment:BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_test

    override fun initial(view: View) {
//        val b = BottomSheetBehavior.from(nsv_mine_fragment_bottom)
//        b.isHideable = false
//        falseb.state = BottomSheetBehavior.STATE_HALF_EXPANDED
    }

}