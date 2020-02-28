package com.example.train.adapter

import android.annotation.SuppressLint
import android.content.Context
import com.example.train.db.entity.TeamMemberBean
import com.example.train.R
import kotlinx.android.synthetic.main.recycle_team_member_item.view.*

/**
 * Created by chenyang
 * on 19-10-22
 */
class TeamMemberRecycleAdapter<T>(private val dataList: List<T>, override val context: Context):
    BaseRecycleAdapter<T>(dataList) {


    override val layoutId: Int
        get() = R.layout.recycle_team_member_item

    @SuppressLint("SetTextI18n")
    override fun creatHolder(holer: BaseViewHolder, t: T) {
        val view = holer.itemView
        t as TeamMemberBean

        view.tv_team_member_name.text = t.name



    }


}