package com.example.train.view.fragment.dialog

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.DisplayMetrics
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.DialogFragment
import com.example.train.R
import kotlinx.android.synthetic.main.fragment_time_picker.*
import java.util.*

/**
 * Created by chenyang
 * on 20-3-12
 */
 class MyTimePickerFragment(val getTime: (hour: String, minute: String) -> Unit) : DialogFragment(),
    View.OnClickListener {

    @SuppressLint("PrivateResource")
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        dialog?.window?.setBackgroundDrawableResource(R.color.mtrl_btn_transparent_bg_color)
        dialog?.window?.setDimAmount(0.4F)
        return inflater.inflate(R.layout.fragment_time_picker, container, false)
    }

    override fun onStart() {
        super.onStart()
        requireActivity().windowManager.defaultDisplay.getMetrics(DisplayMetrics())
        dialog?.window?.setLayout(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        tp_time_pick_fm.setIs24HourView(true)
        tv_time_pick_fm_sure.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.tv_time_pick_fm_sure -> {
                dismissAllowingStateLoss()
                val hour: Int
                val minute: Int
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    hour = tp_time_pick_fm.hour
                    minute = tp_time_pick_fm.minute
                } else {
                    val calendar = Calendar.getInstance()
                    hour = calendar.get(Calendar.HOUR_OF_DAY)
                    minute = calendar.get(Calendar.MINUTE)
                }
                getTime(hour.toString(), minute.toString())
            }
        }
    }
}