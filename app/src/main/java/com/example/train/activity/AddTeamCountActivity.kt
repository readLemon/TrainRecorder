package com.example.train.activity

import android.os.Bundle
import androidx.lifecycle.ViewModelProviders
import com.example.train.R
import com.example.train.viewmodel.AddTeamCountVM
import kotlinx.android.synthetic.main.activity_add_team_count.*

class AddTeamCountActivity : BaseActivity() {

    private lateinit var addTeamCountViewModel: AddTeamCountVM
    override val contentVieId: Int
        get() = R.layout.activity_add_team_count


    override fun initView(savedInstanceState: Bundle?) {
        addTeamCountViewModel = ViewModelProviders.of(this).get(AddTeamCountVM::class.java)

        btn_add_team_count.setOnClickListener {
            addTeamCountViewModel.requestAddTeamCount()
        }

//        btn_add_team_member.visibility = View.INVISIBLE
        btn_add_team_member.setOnClickListener {

            val names = arrayOf("勾建松","刘光炀","李昊川","李子龙","雒泰","潘立夫","傅译平","蒋正道","陈阳",
                "罗彦钦","乜云鹏","向元弟","王涛","蒋英杰","李俊","张纪强","卫卓凡","董瑞钊","周淋佳","王海龙")

            for (name in names) {
                addTeamCountViewModel.requestAddUser(name)
            }
        }
    }

}
