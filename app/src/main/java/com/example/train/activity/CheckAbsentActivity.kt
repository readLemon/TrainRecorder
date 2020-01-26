package com.example.train.activity

import android.os.Bundle
import android.view.LayoutInflater
import androidx.appcompat.app.AlertDialog
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.train.R
import com.example.train.adapter.LeaveRecycleAdapter
import com.example.train.adapter.PersonalRecycleAdapter
import com.example.train.network.interfaces.OnRecycleItemClickedListener
import com.example.train.model.PersonalAbsentModel
import com.example.train.model.TeamAbsentModel
import com.example.train.viewmodel.CheckAbsentVM
import kotlinx.android.synthetic.main.activity_check_absent.*

class CheckAbsentActivity : BaseActivity() {

    private lateinit var currentModel: TeamAbsentModel.Data
    private lateinit var checkAbsentViewModel: CheckAbsentVM
    private val teamAbsentList = ArrayList<TeamAbsentModel.Data>()
    private val personalAbsentList = ArrayList<PersonalAbsentModel.Leave>()
    override val contentVieId: Int
        get() = R.layout.activity_check_absent

    override fun initView(savedInstanceState: Bundle?) {
        checkAbsentViewModel = ViewModelProviders.of(this).get(checkAbsentViewModel::class.java)
        initData()
        initView()
    }

    private fun initData() {

        checkAbsentViewModel.apply {
            requestPersonalData(currentModel.name)
            requestData()
            teamAbsentData.observe(this@CheckAbsentActivity, Observer {
                teamAbsentList.apply {
                    sortBy { it.absent }
                    clear()
                    addAll(it.filter { it.absent != 0 })
                }
            })

            teamCount.observe(this@CheckAbsentActivity, Observer {
                tv_team_count.text = it.get(0).check_count.toString()
            })

            personalAbsent.observe(this@CheckAbsentActivity, Observer {
                personalAbsentList.clear()
                personalAbsentList.addAll(it.leave)
            })
        }
    }

    private fun initView() {

        tb_sign_in.setNavigationOnClickListener { finish() }
        val adapter = LeaveRecycleAdapter<TeamAbsentModel.Data>(teamAbsentList, this)

        rv_absent_list.layoutManager = LinearLayoutManager(this)
        rv_absent_list.adapter = adapter

        adapter.setOnrecycleItemClikedListener(object :
            OnRecycleItemClickedListener<TeamAbsentModel.Data> {
            override fun onItemCliked(bean: TeamAbsentModel.Data) {
                currentModel = bean
                setPersonalAbsentDialog()
            }
        })


    }

    private fun setPersonalAbsentDialog() {

        val baseDialog = AlertDialog.Builder(this)
        val dialogView =
            LayoutInflater.from(this).inflate(R.layout.dialog_personal_absent_list, null)
        baseDialog.setView(dialogView)

        val personalRecycle =
            dialogView.findViewById<RecyclerView>(R.id.rv_dialog_personal_absent_list)
        personalRecycle.layoutManager = LinearLayoutManager(this)
        baseDialog.create()
        val adapter = PersonalRecycleAdapter(personalAbsentList, this@CheckAbsentActivity)
        personalRecycle.adapter = adapter
        //在有数据的情况下，展示弹窗
        baseDialog.show()
    }
}
