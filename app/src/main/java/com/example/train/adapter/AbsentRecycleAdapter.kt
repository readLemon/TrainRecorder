package com.example.train.adapter

import android.content.Context
import com.example.train.Bean.AbsentListBean
import com.example.train.R
import kotlinx.android.synthetic.main.recycle_absent_item.view.*

/**
 * Created by chenyang
 * on 19-9-29 上午10:15
 */
class AbsentRecycleAdapter(private val dataList: List<AbsentListBean.Data>, override val context: Context):
    BaseRecycleAdapter<AbsentListBean.Data>(dataList) {


    override val layoutId: Int
        get() = R.layout.recycle_absent_item


    override fun creatHolder(holer: BaseViewHolder, t: AbsentListBean.Data) {

        val view = holer.itemView
        view.tv_absent_name.text = t.name
        view.tv_absent_num.text = t.absent.toString()

    }



}