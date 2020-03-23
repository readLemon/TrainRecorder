package com.example.train.view.fragment.first

import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.example.train.R
import com.example.train.view.fragment.BaseFragment
import com.example.train.view.fragment.first.ControlTeamFragment.Companion.isInputAvailable
import com.example.train.viewmodel.ControlTeamViewModel
import kotlinx.android.synthetic.main.fragment_add_personal.*
/**
 * Created by chenyang
 * on 20-2-29
 */
class AddPersonalChildTeamFragment(val onFragmentClick: Fragment.(v: View)->Unit={}) : BaseFragment() {
    override val contentViewId: Int
        get() = R.layout.fragment_add_personal

    private val viewModel by viewModels<ControlTeamViewModel>()

    override fun initial(view: View) {
        val project = et_add_personal_fm_project.text.toString().trim()
        val time = et_add_personal_fm_time.text.toString().trim()
        val tempTime = System.currentTimeMillis()
        val reason = et_add_personal_reason.text.toString().trim()
        val where = et_add_personal_fm_where.text.toString().trim()
        val remark = et_add_personal_fm_remark.text.toString().trim()
        onFragmentClick(btn_add_personal_fm_submit)
        btn_add_personal_fm_submit.addListner(View.OnClickListener { btnView ->
            if (isInputAvailable(project, where)) {
                if (isInputAvailable(reason)) {
                    viewModel.addLeave(project = project, time = tempTime, reason = reason).observeNotNull {
                        if (it) {

                        }
                    }

                } else {
                    viewModel.addAbsent(time = tempTime, project = project).observeNotNull {
                        if (it) {
                            onFragmentClick(btnView)
                        }
                    }
                }
            } else {
                Toast.makeText(context, "请输入完整信息", Toast.LENGTH_SHORT).show()
            }
        })

    }

    override fun onDestroy() {
        viewModel.clearDisposable()
        super.onDestroy()
    }
}