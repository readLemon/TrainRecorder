package com.example.train.view.fragment.secon

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import android.animation.ValueAnimator
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.fragment.app.viewModels
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
import com.example.train.viewmodel.TimeViewModel
import com.example.train.widget.SlideLayout
import com.example.train.widget.interfaces.onSlideChangeListener
import com.mredrock.cyxbs.common.utils.LogUtil
import kotlinx.android.synthetic.main.fragment_time.*
import kotlinx.android.synthetic.main.item_signin_situation.view.*
import java.util.*
import kotlin.collections.ArrayList
import kotlin.properties.Delegates


/**
 * Created by chenyang
 * on 20-3-4
 *
 * 倒计时函数用了临时值
 * rv的数据是手动添加的需要修改
 * 团队的成员数量是手动写的需要修改
 */
class TimeFragment : BaseFragment(), View.OnClickListener {
    override val contentViewId: Int
        get() = R.layout.fragment_time

    private val sp by lazy { requireActivity().mySharedPreferences }
    private val viewmodel by viewModels<TimeViewModel>()

    private val members = ArrayList<Member>()
    private var currentClickBean: Member? = null
    private var rvAdapter by Delegates.notNull<CommonRecycAdapter<Member>>()

    private var startSignInTime = 0
    private var endSignInTime = 0

    private var startTime = "00"
    private var endTime = "00"

    private var teamSize = 0
    private val calendar by lazy { Calendar.getInstance() }
    private val countDownAnimator by lazy { ValueAnimator.ofFloat(0F, 1F) }

    override fun initial(view: View) {
        iv_time_fm_add_signin.setOnClickListener(this)
        tv_time_fm_add_signin_start_time.setOnClickListener(this)
        tv_time_fm_add_signin_end_time.setOnClickListener(this)
        btn_time_fm_submit.setOnClickListener(this)
        iv_time_fm_signin_satuation_arrow.setOnClickListener(this)
        viewmodel.getMembersFromDB().observeNotNull {
            teamSize = 15//it.size
        }
        setRecyclerView()
    }

    /**
     * 生成测试数据
     */
    private fun addData() {
        var bea: Member
        for (i in 1..15) {
            bea = Member("我叫陈阳${i}")
            members.add(bea)
        }
    }

    private fun setRecyclerView() {

        var s: SlideLayout? = null
        rvAdapter = CommonRecycAdapter(
            R.layout.item_signin_situation,
            members
        ) { bean ->
            currentClickBean = bean
            this.tv_item_situation_name.text = bean.name
            //这里其实有纠结过要不要换成多监听然后在fragment的监听器做统一处理。。。后来想想又没太大必要就没换了
            this.tv_item_signin_sure.setOnClickListener({
                if (timeOut()) { //为true则是已经迟到了
                    signInWithLate(bean)
                } else {
                    signInWithoutLate(bean)
                }
                s?.closeMenu()
            })

            this.tv_item_signin_absent.setOnClickListener({
                //处理缺勤逻辑
                absent(bean)
            })

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
        rv_time_fm_signin_situation.adapter = rvAdapter
        rv_time_fm_signin_situation.layoutManager = LinearLayoutManager(context)
    }

    /**
     * 缺勤处理逻辑
     */
    private fun absent(bean: Member) {
        members.remove(bean)
        rvAdapter.notifyDataSetChanged()
        sp.getString(SIGN_IN_PROJECT, "signInAbsent")
            ?.let { viewmodel.addAbsent(calendar.time.time, it) }
        if (members.isEmpty()) {
            onMembersEmpty()
        }
        tv_num_of_unsignin.setText("剩余未签到人数: ${members.size}")
    }

    /**
     * 签到
     * 准时处理逻辑
     */
    private fun signInWithoutLate(bean: Member) {
        members.remove(bean)
        rvAdapter.notifyDataSetChanged()
        tv_num_of_signed.setText("已签到人数: ${teamSize - members.size}")
        tv_num_of_unsignin.setText("剩余未签到人数: ${members.size}")
        if (members.isEmpty()) {
            onMembersEmpty()
        }
    }

    /**
     * 签到
     * 迟到处理逻辑
     */
    private fun signInWithLate(bean: Member) {
        members.remove(bean)
        rvAdapter.notifyDataSetChanged()
        //迟到了多长时间
        val lateDuration =
            calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE) - endSignInTime
        sp.getString(SIGN_IN_PROJECT, "signInLate")
            ?.let { viewmodel.addLate(calendar.time.time, lateDuration, it) }
        if (members.isEmpty()) {
            onMembersEmpty()
        }
        tv_num_of_signed.setText("已签到人数: ${teamSize - members.size}")
        tv_num_of_unsignin.setText("剩余未签到人数: ${members.size}")
    }

    /**
     * 在一次签到中，所有的人都已经签到或者缺勤签到
     */
    private fun onMembersEmpty() {
        sp.editor {
            putInt(SIGN_IN_START_TIME, -1)
            putInt(SIGN_IN_END_TIME, -1)
        }
    }

    private fun timeOut(): Boolean {
        endSignInTime = sp.getInt(SIGN_IN_END_TIME, -1)
        if (endSignInTime != -1) {
            val curHour = calendar.get(Calendar.HOUR_OF_DAY)
            val curMinute = calendar.get(Calendar.MINUTE)
            if (endSignInTime < curHour * 60 + curMinute) {   //已经迟到了
                return true
            }

        } else {
            throw RuntimeException("签到时间没在SharedPreference记录成功！")
        }
        return false
    }

    override fun onClick(v: View) {
        when (v.id) {
            //添加打卡
            R.id.iv_time_fm_add_signin -> {
                if (UserUtil.isCaptain) {
                    if (hasSignTask()) {
                        ToastUtil.showMsg("对不起，已经有一个签到正在进行！")
                        return
                    }
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
                    et_time_fm_project.text?.isEmpty() ?: false ||
                    et_time_fm_where.text?.isEmpty() ?: false
                ) {
                    showToast("请设置完整签到信息！")
                } else {
                    if (startSignInTime < endSignInTime) {
                        sp.editor {
                            putInt(
                                SIGN_IN_START_TIME,
                                startSignInTime
                            )
                            putInt(
                                SIGN_IN_END_TIME,
                                endSignInTime
                            )
                            putString(SIGN_IN_PROJECT, et_time_fm_project.text.toString())
                            putString(SIGN_IN_WHERE, et_time_fm_where.text.toString())
                        }
                        if (tv_current_signin_end_time.visibility != View.VISIBLE) {
                            tv_current_signin_end_time.visibility = View.VISIBLE
                        }
                        tv_time_fm_has_sign_task.text = "当前有一个签到任务正在执行！"
                        startTime = String.format("%02d", endSignInTime / 60)
                        endTime = String.format("%02d", endSignInTime % 60)
                        tv_current_signin_end_time.text = "签到结束时间: ${startTime}:${endTime}"
                        startCountDown()
                        addData()
                        cl_time_fm_add.visibility = View.GONE
                        tv_num_of_unsignin.setText("剩余未签到人数: ${members.size}")
                        ToastUtil.showMsg("现在开始签到！")

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
//            duration = ((endSignInTime - startSignInTime) * 60 * 1000).toLong()
            duration = (0.5 * 60 * 1000).toLong()
            addListener(object : AnimatorListenerAdapter() {
                override fun onAnimationEnd(animation: Animator) {
                    tv_time_fm_num_of_late.setText("迟到人数: ${members.size}")
                    onSignFinished()
                }
            })
        }
        countDownAnimator.start()
    }

    private fun restartCountDown() {
        LogUtil.d(TAG,"执行了restartCountDown")
        if (!countDownAnimator.isRunning) {
            if (endSignInTime == 0) {

                if (!hasSignTask()) {
                    //这种情况说明，并没有未完成的签到
                    return
                }
                val curTime =
                    calendar.get(Calendar.HOUR_OF_DAY) * 60 + calendar.get(Calendar.MINUTE)
                members.clear()
                viewmodel.getAllUnsignedMemer().observeNotNull {
                    LogUtil.d(TAG,"数据库中的未签到的人数为${it.size}")
                    var member: Member
                    for (entity in it) {
                        member = Member(entity.name)
                        members.add(member)
                        rvAdapter.notifyDataSetChanged()
                        LogUtil.d(TAG, "更新了未签到的名单")
                    }
                }
                if (endSignInTime > curTime && curTime > startSignInTime) {
                    //未超时,重新设置计时器进行倒计时
                    countDownAnimator.apply {
                        interpolator = LinearInterpolator()
                        duration = ((endSignInTime - curTime) * 60 * 1000).toLong()
//                        duration = (0.5 * 60 * 1000).toLong()
                        addListener(object : AnimatorListenerAdapter() {
                            override fun onAnimationEnd(animation: Animator) {
                                tv_time_fm_num_of_late.setText("迟到人数: ${members.size}")
                                onSignFinished()
                            }
                        })
                    }
                    countDownAnimator.start()
                } else if (curTime < startSignInTime) {
                    ToastUtil.showMsg("说实话，这种情况我还没写提前设置签到的处理逻辑！")
                }
            }
            countDownAnimator.start()
        }
    }

    /**
     * 当一次签到执行完毕以后将要执行的操作
     */
    private fun onSignFinished() {

    }

    /**
     * 用来判断是否有一个签到任务正在进行
     */
    private fun hasSignTask(): Boolean {
        val time = sp.getInt(SIGN_IN_START_TIME, -1)
        return time != -1
    }

    override fun onStart() {
        super.onStart()
        restartCountDown()
    }

    override fun onDestroyView() {
        if (countDownAnimator.isRunning) {
            countDownAnimator.cancel()
        }
        super.onDestroyView()
        LogUtil.d(TAG, "*****onDestroyView*****")
    }

    override fun onStop() {
        super.onStop()
        LogUtil.d(TAG, "*****onStop*****")
    }

    override fun onPause() {
        super.onPause()
        LogUtil.d(TAG,"存储了未签到的人数为${members.size}")
        viewmodel.deleteAllUnsignedMember()
        viewmodel.saveUnsignedMember(members)
    }



    companion object {
        const val TAG = "TimeFragment"
        const val SIGN_IN_END_TIME = "SIGN_IN_END_TIME"
        const val SIGN_IN_START_TIME = "SIGN_IN_START_TIME"
        const val SIGN_IN_PROJECT = "SIGN_IN_PROJECT"
        const val SIGN_IN_WHERE = "SIGN_IN_WHERE"

    }
}