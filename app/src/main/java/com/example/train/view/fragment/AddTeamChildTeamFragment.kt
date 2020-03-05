package com.example.train.view.fragment

import android.view.View
import androidx.fragment.app.viewModels
import com.example.train.R
import com.example.train.view.fragment.BaseFragment
import com.example.train.viewmodel.ControlTeamViewModel
import kotlinx.android.synthetic.main.fragment_add_team.*

/**
 * Created by chenyang
 * on 20-2-29
 */
class AddTeamChildTeamFragment: BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_add_team

    private val viewModel by viewModels<ControlTeamViewModel>()

    override fun initial(view: View) {
        var project: String
        var time: Long
        var where: String
        var remark: String
        btn_add_team_submit.setOnClickListener {
            project = et_add_team_fm_project.text.toString().trim()
            time = System.currentTimeMillis()//et_add_team_fm_time.text.toString().trim()
            where = et_add_team_fm_where.text.toString().trim()
            remark = et_add_team_fm_remark.text.toString().trim()
            if(ControlTeamFragment.isInputAvailable(project, where, remark)) {
                viewModel.addTeamData(project, time)
            }
        }
    }
}