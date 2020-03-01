package com.example.train.view.fragment

import android.view.View
import androidx.navigation.NavAction
import androidx.navigation.NavDirections
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.train.R
import kotlinx.android.synthetic.main.fragment_main.*

/**
 * Created by chenyang
 * on 20-2-27
 */
class MainFragment: BaseFragment(), View.OnClickListener {
    override val contentViewId: Int
        get() = R.layout.fragment_main

    override fun initial(view: View) {
        iv_main_fm_lu.setOnClickListener(this)
        iv_main_fm_ru.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        var action: NavDirections ?= null
        when(v.id){
            R.id.iv_main_fm_lu -> action = MainFragmentDirections.actionFragmentMainToFragmentMine()
            R.id.iv_main_fm_ru -> action = MainFragmentDirections.actionFragmentMainToFragmenControlTeam()
            R.id.iv_main_fm_ld -> action = MainFragmentDirections.actionFragmentMainToFragmenControlTeam()
            R.id.iv_main_fm_rd -> action = MainFragmentDirections.actionFragmentMainToFragmenControlTeam()
        }
        action?.let { v.findNavController().navigate(it) }
    }


}