package com.example.train.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.train.Bean.AbsentBean
import com.example.train.R
import com.example.train.adapter.AbsentRecycleAdapter
import kotlinx.android.synthetic.main.activity_sign_in.*

class SignInActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_sign_in)

        initView()

    }

    private fun initView() {
        val absentList = ArrayList<AbsentBean>()

        val adapter = AbsentRecycleAdapter(absentList, this)
        rv_absent_list.layoutManager = LinearLayoutManager(this)
        rv_absent_list.adapter = adapter

    }
}
