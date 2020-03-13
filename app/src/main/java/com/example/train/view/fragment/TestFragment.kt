package com.example.train.view.fragment

import android.view.View
import com.example.train.R
import com.example.train.util.UserUtil
import com.example.train.util.extention.editor
import com.example.train.util.extention.mySharedPreferences
import com.example.train.view.fragment.BaseFragment
import com.example.train.view.fragment.dialog.MyTimePickerFragment
import kotlinx.android.synthetic.main.fragment_add_personal.*
import kotlinx.android.synthetic.main.fragment_time.*
import kotlin.properties.Delegates


/**
 * Created by chenyang
 * on 20-3-4
 */
class TestFragment : BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_test
}