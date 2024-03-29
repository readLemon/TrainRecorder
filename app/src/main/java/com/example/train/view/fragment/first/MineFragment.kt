package com.example.train.view.fragment.first

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
import com.example.train.util.UserUtil
import com.example.train.view.fragment.BaseFragment
import com.example.train.view.fragment.MineFragmentDirections
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
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
            lcv_mine_fm_leave.startAnim(2000L)
            lcv_mine_fm_absent.startAnim(2000L)
            viewModel.getAbsents(username = UserUtil.currentUsername)
            viewModel.getLeaves(username = UserUtil.currentUsername)

        }
        setView()

        viewModel.leaves.observeNotNull {
            leaveBeans.addAll(it)
        }

        viewModel.absents.observeNotNull {
            absentBeans.addAll(it)
        }

        leaveRecycApapter = CommonRecycAdapter(
            R.layout.item_rv_mine_fragment_bottom,
            leaveBeans,
            {bean ->
                tv_rv_item_project_content.text = bean.leaveProject
                tv_rv_item_time_content.text = bean.leaveTime.toString()
                tv_rv_item_where_content.text = bean.leaveReason
            }
        )

        absentRecycApapter = CommonRecycAdapter(
            R.layout.item_rv_mine_fragment_bottom,
            absentBeans,
            {bean ->
                tv_rv_item_project_content.text = bean.absentProject
                tv_rv_item_time_content.text = bean.absentTime.toString()
            }
        )

        rv_mine_fm_show_leave_absent.adapter = leaveRecycApapter
        rv_mine_fm_show_leave_absent.layoutManager = LinearLayoutManager(context)
    }



    private fun setView() {
        tb_mine_fm_leave_absent.setNavigationOnClickListener {
            val action =
                MineFragmentDirections.actionFragmentMineToFragmentMain()
            Navigation.findNavController(it).navigate(action)
        }

        bottomSheetBehavior = BottomSheetBehavior.from(nsv_mine_fragment_bottom)
        bottomSheetBehavior.state = BottomSheetBehavior.STATE_HIDDEN


        btn_mine_fm_show_leave_detail.setOnClickListener(this)
        btn_mine_fm_show_absent_detail.setOnClickListener(this)

        tv_mine_fm_leave_cnt.setText("请假次数：${leaveBeans.size}")
        tv_mine_fm_absent_cnt.setText("请假次数：${absentBeans.size}")

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