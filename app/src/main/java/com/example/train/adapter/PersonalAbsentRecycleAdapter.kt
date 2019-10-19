package com.example.train.adapter

import android.content.Context
import com.example.train.Bean.PersonalAbsentBean
import com.example.train.R

/**
 * Created by chenyang
 * on 19-10-19
 */
class PersonalAbsentRecycleAdapter(val dataList: List<PersonalAbsentBean>, override val context: Context):
    BaseRecycleAdapter<PersonalAbsentBean>(dataList) {

    override val layoutId: Int
        get() = R.layout.recycle_personal_absent_item



    override fun creatHolder(holer: BaseViewHolder, t: PersonalAbsentBean) {


    }
}