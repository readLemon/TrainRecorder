package com.example.train.view.fragment.secon

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.train.R
import com.example.train.bean.Member
import com.example.train.util.ToastUtil
import com.example.train.util.UserUtil
import com.example.train.util.extention.editor
import com.example.train.util.extention.mySharedPreferences
import com.example.train.view.fragment.BaseFragment
import com.example.train.view.fragment.adapter.CommonRecycAdapter
import com.example.train.view.fragment.dialog.MyTimePickerFragment
import com.example.train.widget.SlideLayout
import com.example.train.widget.interfaces.onSlideChangeListener
import kotlinx.android.synthetic.main.fragment_add_personal.*
import kotlinx.android.synthetic.main.fragment_time.*
import kotlinx.android.synthetic.main.item_signin_situation.view.*
import kotlin.properties.Delegates


/**
 * Created by chenyang
 * on 20-3-4
 */
class TimeFragment : BaseFragment(), View.OnClickListener {
    override val contentViewId: Int
        get() = R.layout.fragment_time

    private val sp by lazy { requireActivity().mySharedPreferences }
    private val members = ArrayList<Member>()
    private var currentClickBean: Member? = null
    private var rvAdapter by Delegates.notNull<CommonRecycAdapter<Member>>()
    private var startSignInTime = 0
    private var endSignInTime = 0

    private var startTime = "00"
    private var endTime = "00"

    private val countDownAnimator by lazy { ValueAnimator.ofFloat(0F, 1F) }

    override fun initial(view: View) {
        iv_time_fm_add_signin.setOnClickListener(this)
        tv_time_fm_add_signin_start_time.setOnClickListener(this)
        tv_time_fm_add_signin_end_time.setOnClickListener(this)
        btn_time_fm_submit.setOnClickListener(this)
        iv_time_fm_signin_satuation_arrow.setOnClickListener(this)
        setRecyclerView()
    }

    private fun setRecyclerView() {

        var bea: Member
        for (i in 1..15) {
            bea = Member("我叫陈阳${i}")
            members.add(bea)
        }
        var s: SlideLayout? = null
        rvAdapter = CommonRecycAdapter(
            R.layout.item_signin_situation,
            members,
            { bean ->
                currentClickBean = bean
                this.tv_item_situation_name.text = bean.name
                //这里其实有纠结过要不要换成多监听然后在fragment的监听器做统一处理。。。后来想想又没太大必要就没换了
                this.tv_item_signin_sure.setOnClickListener {
                    members.remove(bean)
                    s?.closeMenu()
                    rvAdapter.notifyDataSetChanged()
                }
                (this.tv_item_situation_name.rootView as SlideLayout).slideListener =
                    object : onSlideChangeListener {
                        override fun onMenuOpen(slide: SlideLayout) {
                            s = slide
                        }

                        override fun onMenuClose(slide: SlideLayout) {
                            if (s != null) {
                                s = null
                            }
                        }

                        override fun onMenuClick(slide: SlideLayout) {
                            s?.closeMenu()
                        }
                    }
            }
        )

        rv_time_fm_signin_situation.adapter = rvAdapter
        rv_time_fm_signin_situation.layoutManager = LinearLayoutManager(context)

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
                    startSignInTime = hour * 60 + minute
                    startTime = String.format("%02d", hour)
                    endTime = String.format("%02d", minute)
                    tv_time_fm_add_signin_start_time.setText("考勤开始时间: ${startTime}:${endTime}")
                }).show(childFragmentManager, "选择考勤开始时间")
            }
            //结束打卡的考勤的时间
            R.id.tv_time_fm_add_signin_end_time -> {
                MyTimePickerFragment({ hour, minute ->
                    endSignInTime = hour * 60 + minute
                    startTime = String.format("%02d", hour)
                    endTime = String.format("%02d", minute)
                    tv_time_fm_add_signin_end_time.setText("考勤结束时间: ${startTime}:${endTime}")
                }).show(childFragmentManager, "选择考勤结束时间")

                if (endSignInTime < startSignInTime) {
                    showToast("请选择一个合适的结束时间！")
                }
                tv_time_fm_add_signin_end_time.setText("")
            }

            R.id.btn_time_fm_submit -> {
                if (tv_time_fm_add_signin_start_time.text.isEmpty() ||
                    tv_time_fm_add_signin_end_time.text.isEmpty() ||
                    et_add_personal_fm_project.text?.isEmpty() ?: false ||
                    et_time_fm_where.text?.isEmpty() ?: false
                ) {
                    showToast("请设置完整签到信息！")
                } else {
                    if (startSignInTime > endSignInTime) {
                        sp.editor {
                            putString(
                                "SIGN_IN_START_TIME",
                                "${startSignInTime / 60}:${startSignInTime % 60}"
                            )
                            putString(
                                "SIGN_IN_END_TIME",
                                "${endSignInTime / 60}:${endSignInTime % 60}"
                            )
                            putString("SIGN_IN_PROJECT", et_time_fm_project.text.toString())
                            putString("SIGN_IN_WHERE", et_time_fm_where.text.toString())
                        }
                        if (tv_current_signin_end_time.visibility != View.VISIBLE) {
                            tv_current_signin_end_time.visibility = View.VISIBLE
                        }
                        tv_time_fm_has_sign_task.text = "当前有一个签到任务正在执行！"
                        startTime = String.format("%02d", endSignInTime / 60)
                        endTime = String.format("%02d", endSignInTime % 60)
                        tv_current_signin_end_time.text = "签到结束时间: ${startTime}:${endTime}"
                        startCountDown()
                    } else {
                        ToastUtil.showMsg("请选择正确的开始结束时间！")
                    }
                }

            }

            R.id.iv_time_fm_signin_satuation_arrow -> {
                if (rv_time_fm_signin_situation.visibility != View.VISIBLE) {
                    rv_time_fm_signin_situation.visibility = View.VISIBLE
                    iv_time_fm_signin_satuation_arrow.rotation = -90F
                } else {
                    rv_time_fm_signin_situation.visibility = View.GONE
                    iv_time_fm_signin_satuation_arrow.rotation = 180F
                }
            }
        }
    }

    /**
     * 开始进行倒计时
     */
    private fun startCountDown() {
        countDownAnimator.apply {
            interpolator = LinearInterpolator()
            duration = (endSignInTime - startSignInTime).toLong()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    
                }
            })
        }
    }

    private fun resetCountDown() {
        if (countDownAnimator.isRunning) {
            countDownAnimator.cancel()
        }
    }
}