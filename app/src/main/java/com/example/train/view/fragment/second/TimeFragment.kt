package com.example.train.view.fragment.secon

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
class TimeFragment : BaseFragment(), View.OnClickListener {
    override val contentViewId: Int
        get() = R.layout.fragment_time
    private var startTime by Delegates.notNull<String>()
    private var endTime by Delegates.notNull<String>()
    val sp by lazy { requireActivity().mySharedPreferences }

    override fun initial(view: View) {
        iv_time_fm_add_signin.setOnClickListener(this)
        tv_time_fm_add_signin_start_time.setOnClickListener(this)
        tv_time_fm_add_signin_end_time.setOnClickListener(this)
        btn_time_fm_submit.setOnClickListener(this)

    }

    override fun onClick(v: View) {
        when (v.id) {
            //添加打卡
            R.id.iv_time_fm_add_signin -> {
                if (UserUtil.isCaptain) {
                    if (cl_time_fm_add.visibility != View.VISIBLE) {
                        cl_time_fm_add.visibility = View.VISIBLE
                        iv_time_fm_add_signin.setImageResource(R.drawable.ic_back_gray)
                        iv_time_fm_add_signin.rotation = -90f
                    } else {
                        cl_time_fm_add.visibility = View.GONE
                        iv_time_fm_add_signin.setImageResource(R.drawable.ic_add)
                    }
                } else {
                    showToast("请向队长申请权限！")
                }
            }
            //开始打卡考勤的时间
            R.id.tv_time_fm_add_signin_start_time -> {
                MyTimePickerFragment({ hour, minute ->
                    startTime = "${hour}:${minute}"
                    tv_time_fm_add_signin_start_time.setText("考勤开始时间: ${startTime}")
                }).show(childFragmentManager, "选择考勤开始时间")
            }
            //结束打卡的考勤的时间
            R.id.tv_time_fm_add_signin_end_time -> {
                MyTimePickerFragment({ hour, minute ->
                    endTime = "${hour}:${minute}"
                    tv_time_fm_add_signin_end_time.setText("考勤结束时间: ${endTime}")
                }).show(childFragmentManager, "选择考勤结束时间")
            }

            R.id.btn_time_fm_submit -> {
                if (tv_time_fm_add_signin_start_time.text.isEmpty() ||
                    tv_time_fm_add_signin_end_time.text.isEmpty() ||
                    et_add_personal_fm_project.text?.isEmpty() ?: false ||
                    et_time_fm_where.text?.isEmpty() ?: false
                ) {
                    showToast("请填写完整签到信息！")
                } else {
                    sp.editor {
                        putString("SIGN_IN_START_TIME", startTime)
                        putString("SIGN_IN_END_TIME", endTime)
                        putString("SIGN_IN_PROJECT", et_time_fm_project.text.toString())
                        putString("SIGN_IN_WHERE", et_time_fm_where.text.toString())
                    }
                    if (tv_current_signin_end_time.visibility != View.VISIBLE) {
                        tv_current_signin_end_time.visibility = View.VISIBLE
                    }
                    tv_time_fm_has_sign_task.text = "当前有一个签到任务正在执行！"
                    tv_current_signin_end_time.text = "签到结束时间: ${endTime}"
                }
            }
        }
    }
}