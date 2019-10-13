package com.example.train.adapter

import android.content.Context
import android.widget.AdapterView
import android.widget.Button
import com.example.train.Bean.AbsentBean
import com.example.train.R
import com.example.train.interfaces.OnRecycleItemClickedListener
import kotlinx.android.synthetic.main.recycle_absent_item.view.*
import java.security.AllPermission

/**
 * Created by chenyang
 * on 19-9-29 上午10:15
 */
class AbsentRecycleAdapter(private val date: List<AbsentBean>, context: Context): BaseRecycleAdapter<AbsentBean>(date) {


    override val layoutId: Int
        get() = R.layout.recycle_absent_item
    override val context: Context
        get() = context



    override fun creatHolder(holer: BaseViewHolder, t: AbsentBean) {

        val view = holer.itemView
        view.tv_absent_name.text = t.name
        view.tv_absent_num.text = t.num

    }



}