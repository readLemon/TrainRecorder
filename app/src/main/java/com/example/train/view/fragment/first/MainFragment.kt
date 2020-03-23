package com.example.train.view.fragment.first

import android.view.View
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.navigation.NavDirections
import androidx.navigation.findNavController
import com.example.train.R
import com.example.train.view.fragment.BaseFragment
import com.example.train.view.fragment.MainFragmentDirections
import com.example.train.viewmodel.MainViewModel
import com.mredrock.cyxbs.common.utils.LogUtil
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by chenyang
 * on 20-2-27
 */
class MainFragment : BaseFragment(), View.OnClickListener {
    override val contentViewId: Int
        get() = R.layout.fragment_main

    private val viewmodel by viewModels<MainViewModel>()


    override fun initial(view: View) {
        iv_main_fm_lu.setOnClickListener(this)
        iv_main_fm_ru.setOnClickListener(this)
        viewmodel.initMembers()
    }

    override fun onClick(v: View) {
        var action: NavDirections? = null
        when (v.id) {
            R.id.iv_main_fm_lu -> action =
                MainFragmentDirections.actionFragmentMainToFragmentMine()
            R.id.iv_main_fm_ru -> action =
                MainFragmentDirections.actionFragmentMainToFragmenControlTeam()
            R.id.iv_main_fm_ld -> action =
                MainFragmentDirections.actionFragmentMainToFragmenControlTeam()
            R.id.iv_main_fm_rd -> action =
                MainFragmentDirections.actionFragmentMainToFragmenControlTeam()
            R.id.cv_main_fm_lu -> Toast.makeText(context, "被点击了", Toast.LENGTH_SHORT).show()
        }
        action?.let { v.findNavController().navigate(it) }
    }


    override fun onDetach() {
        super.onDetach()
        LogUtil.e("*************mmma******", "*onDetach*****")

    }

    override fun onDestroy() {
        super.onDestroy()
        LogUtil.e("*************mmma******", "*onDestroy*****")

    }


}