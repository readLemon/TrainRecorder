package com.example.train.adapter

import android.content.Context
import com.example.train.R
import kotlinx.android.synthetic.main.recycle_absent_item.view.*

/**
 *
 * Created by chenyang
 * on 19-9-29
 *
 */
class LeaveRecycleAdapter<T>(private val dataList: List<T>, override val context: Context):
    BaseRecycleAdapter<T>(dataList) {

    override val layoutId: Int
        get() = R.layout.recycle_absent_item

    override fun creatHolder(holer: BaseViewHolder, t: T) {
        t as TeamAbsentBean.Data
        val view = holer.itemView
        view.tv_absent_name.text = t.name
        view.tv_absent_num.text = (t.leave_times - t.absent).toString()
        view.tv_leave_absent_num.text = t.absent.toString()
    }

}