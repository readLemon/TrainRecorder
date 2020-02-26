package com.example.train.view.activity

import android.content.Intent
import android.os.Bundle
import android.view.View
import com.example.train.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : BaseActivity(), View.OnClickListener {
    override val contentVieId: Int
        get() = R.layout.activity_main


    override fun initView(savedInstanceState: Bundle?) {
        initView()
    }


    private fun initView() {
        btn_goto_add_absent_activity.setOnClickListener(this)
        btn_goto_check_absent_activity.setOnClickListener(this)
        btn_goto_add_teamCount_activity.setOnClickListener(this)
    }


    override fun onClick(v: View) {

//        when(v.id) {
//            R.id.btn_goto_add_absent_activity -> {
//                val intent = Intent(this, AddLeaveActivity::class.java)
//                startActivity(intent)
//            }
//
//            R.id.btn_goto_check_absent_activity -> {
//                val intent = Intent(this, CheckAbsentActivity::class.java)
//                startActivity(intent)
//            }
//
//            R.id.btn_goto_add_teamCount_activity -> {
//                val intent = Intent(this, AddTeamCountActivity::class.java)
//                startActivity(intent)
//            }


//        }

    }
}
