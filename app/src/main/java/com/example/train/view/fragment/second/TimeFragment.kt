package com.example.train.view.fragment.second

import android.app.AlertDialog
import android.app.TimePickerDialog
import android.view.ActionMode
import android.view.View
import android.widget.TimePicker
import androidx.navigation.fragment.findNavController
import com.example.train.R
import com.example.train.util.DensityUtil
import com.example.train.view.fragment.BaseFragment
import com.mredrock.cyxbs.common.utils.LogUtil
import kotlinx.android.synthetic.main.fragment_time.*
import java.util.*

/**
 * Created by chenyang
 * on 20-3-4
 */
class TimeFragment: BaseFragment(), View.OnClickListener {
    override val contentViewId: Int
        get() = R.layout.fragment_time
    private val calendar = Calendar.getInstance()

    override fun initial(view: View) {
        iv_time_fm_add_signin.setOnClickListener(this)
        tv_time_fm_add_signin_start_time.setOnClickListener(this)
        tv_time_fm_add_signin_end_time.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when(v.id) {
            R.id.iv_time_fm_add_signin -> {
                if (cl_time_fm_add.visibility != View.VISIBLE) {
                    cl_time_fm_add.visibility = View.VISIBLE
                    iv_time_fm_add_signin.setImageResource(R.drawable.ic_back_gray)
                    iv_time_fm_add_signin.rotation = -90f
                }else{
                    cl_time_fm_add.visibility = View.GONE
                    iv_time_fm_add_signin.setImageResource(R.drawable.ic_add)
                }

            }

            R.id.tv_time_fm_add_signin_start_time -> {

                val dia = TimePickerDialog(context,R.style.MyDatePickerDialogTheme,
                    TimePickerDialog.OnTimeSetListener { view, hourOfDay, minute ->

                        showToast("hour: ${hourOfDay}, minute: ${minute}")

                    }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true)
                dia.setCanceledOnTouchOutside(false)
                dia.show()
            }
        }
    }
}