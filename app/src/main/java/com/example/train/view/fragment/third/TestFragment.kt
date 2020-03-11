package com.example.train.view.fragment.third

import android.view.View
import com.example.train.R
import com.example.train.view.fragment.BaseFragment
import com.example.train.widget.QTouchListener
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_test.*

/**
 * Created by chenyang
 * on 20-3-4
 */
class TestFragment: BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_test

    override fun initial(view: View) {
//        test_root.setOnTouchListener(QTouchListener())
    }
}