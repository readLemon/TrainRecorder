package com.example.train.view.fragment

import android.os.Build
import android.view.View
import android.widget.Toast
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.fragment.app.viewModels
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.train.R
import com.example.train.bean.AbsentBean
import com.example.train.bean.LeaveBean
import com.example.train.view.fragment.adapter.CommonRecycAdapter
import com.example.train.viewmodel.MineViewModel
import com.google.android.material.bottomsheet.BottomSheetBehavior
import kotlinx.android.synthetic.main.fragment_mine.*
import kotlinx.android.synthetic.main.item_rv_mine_fragment_bottom.view.*

/**
 * Created by chenyang
 * on 20-2-27
 */
class MineFragment : BaseFragment(), View.OnClickListener {
    override val contentViewId: Int
        get() = R.layout.fragment_mine
    val leaveBeans by lazy { ArrayList<LeaveBean>() }
    val absentBeans by lazy { ArrayList<AbsentBean>() }
    val viewModel by viewModels<MineViewModel>()

    private var isAdapterLeave = true
    private lateinit var leaveRecycApapter: CommonRecycAdapter<LeaveBean>
    private lateinit var absentRecycApapter: CommonRecycAdapter<AbsentBean>
    private lateinit var bottomSheetBehavior: BottomSheetBehavior<ConstraintLayout>


    override fun initial(view: View) {
        setView()
        var l: LeaveBean
        for (i in 1..20) {
            l = LeaveBean("ddd", "ddddd", System.currentTimeMillis())
            leaveBeans.add(l)
        }
        leaveRecycApapter = CommonRecycAdapter(
            R.layout.item_rv_mine_fragment_bottom,
            leaveBeans,
            {
                tv_rv_item_project_content.text = it.leaveProject
                tv_rv_item_time_content.text = it.leaveTime.toString()
                tv_rv_item_where_content.text = it.leaveReason
            }
        )

        absentRecycApapter = CommonRecycAdapter(
            R.layout.item_rv_mine_fragment_bottom,
            absentBeans, {
                tv_rv_item_project_content.text = it.absentProject
                tv_rv_item_time_content.text = it.absentTime.toString()
            }
        )

        rv_mine_fm_show_leave_absent.adapter = leaveRecycApapter
        rv_mine_fm_show_leave_absent.layoutManager = LinearLayoutManager(context)
    }

    private fun setView() {
        tb_mine_fm_leave_absent.setNavigationOnClickListener {
            val action = MineFragmentDirections.actionFragmentMineToFragmentMain()
            Navigation.findNavController(it).navigate(action)
        }


        bottomSheetBehavior = BottomSheetBehavior.from(nsv_mine_fragment_bottom)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN



        btn_mine_fm_show_leave_detail.setOnClickListener(this)
        btn_mine_fm_show_absent_detail.setOnClickListener(this)

        tv_mine_fm_leave_cnt.setText("请假次数：${leaveBeans.size}")
        tv_mine_fm_absent_cnt.setText("请假次数：${absentBeans.size}")

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            lcv_mine_fm_leave.startAnim(2000L)
            lcv_mine_fm_absent.startAnim(2000L)
        }
    }

    override fun onClick(v: View) {
        when (v.id) {
            R.id.btn_mine_fm_show_leave_detail -> {
                if (isAdapterLeave && bottomSheetBehavior.state == BottomSheetBehavior.STATE_EXPANDED) {
                    Toast.makeText(context, "当前已经是显示的请假记录", Toast.LENGTH_SHORT).show()
                } else if (isAdapterLeave && bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                } else {
                    rv_mine_fm_show_leave_absent.adapter = leaveRecycApapter
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    isAdapterLeave = true
                }
            }
            R.id.btn_mine_fm_show_absent_detail -> {
                if (isAdapterLeave) {
                    rv_mine_fm_show_leave_absent.adapter = absentRecycApapter
                    if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                        bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                    }
                    isAdapterLeave = false
                } else if (bottomSheetBehavior.state != BottomSheetBehavior.STATE_EXPANDED) {
                    bottomSheetBehavior.state = BottomSheetBehavior.STATE_EXPANDED
                } else {
                    Toast.makeText(context, "当前已经是显示的缺勤记录", Toast.LENGTH_SHORT).show()
                }
            }
        }
    }

    companion object {
        private val TAG = "MineFragment"
    }

    override fun onDestroy() {
        viewModel.clearDisposable()
        super.onDestroy()
    }
}