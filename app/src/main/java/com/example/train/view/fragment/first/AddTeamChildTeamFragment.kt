package com.example.train.view.fragment.first

import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.train.R
import com.example.train.view.fragment.BaseFragment
import com.example.train.viewmodel.ControlTeamViewModel
import kotlinx.android.synthetic.main.fragment_add_team.*

/**
 * Created by chenyang
 * on 20-2-29
 */
class AddTeamChildTeamFragment(val onFragmentClick: Fragment.(v: View) -> Unit = {}) :
    BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_add_team

    private val viewModel by viewModels<ControlTeamViewModel>()

    override fun initial(view: View) {
        onFragmentClick(btn_add_team_submit)
        var project: String
        var time: Long
        var where: String
        var remark: String
        btn_add_team_submit.setOnClickListener { btnView ->
            project = et_add_team_fm_project.text.toString().trim()
            time = System.currentTimeMillis()//et_add_team_fm_time.text.toString().trim()
            where = et_add_team_fm_where.text.toString().trim()
            remark = et_add_team_fm_remark.text.toString().trim()
            if (ControlTeamFragment.isInputAvailable(project, where, remark)) {
                viewModel.addTeamData(project, time).observeNotNull {
                    if (it) {
                        //加数据成功以后，调用这个询问回调
                        onFragmentClick(btnView)
                    }
                }
            }
        }
    }

    override fun onDestroy() {
        viewModel.clearDisposable()
        super.onDestroy()
    }
}