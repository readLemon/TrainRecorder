package com.example.train.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.util.Log
import com.example.train.Bean.PersonalAbsentBean
import com.example.train.R
import kotlinx.android.synthetic.main.recycle_personal_absent_item.view.*
import kotlin.math.log

/**
 * Created by chenyang
 * on 19-10-22
 */
class PersonalRecycleAdapter<T>(private val dataList: List<T>, override val context: Context):
    BaseRecycleAdapter<T>(dataList) {


    override val layoutId: Int
        get() = R.layout.recycle_personal_absent_item

    @SuppressLint("SetTextI18n")
    override fun creatHolder(holer: BaseViewHolder, t: T) {
        val view = holer.itemView
        t as PersonalAbsentBean.Leave

        view.tv_dialog_absent_date.text = " "+t.time
        view.tv_dialog_absent_project.text = " "+t.project
        view.tv_dialog_absent_reason.text = " "+t.reason

    }


}