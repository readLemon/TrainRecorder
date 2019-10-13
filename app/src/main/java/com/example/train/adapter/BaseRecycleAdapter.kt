package com.example.train.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import androidx.recyclerview.widget.RecyclerView
import com.example.train.interfaces.OnRecycleItemClickedListener

/**
 * Created by chenyang
 * on 19-9-29 上午10:00
 */
abstract class BaseRecycleAdapter<T> (val data: List<T>) : RecyclerView.Adapter<BaseRecycleAdapter.BaseViewHolder>() {

    internal abstract val layoutId: Int
    internal abstract val context: Context
    var listener: OnRecycleItemClickedListener ?= null



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BaseViewHolder {
        val view = LayoutInflater.from(context).inflate(layoutId, parent, false)
        val holder = BaseViewHolder(view)
        return holder
    }

    override fun onBindViewHolder(holder: BaseViewHolder, position: Int) {
        creatHolder(holder, data.get(position))
        listener!!.onItemCliked()
    }

    override fun getItemCount(): Int {
        return data.size
    }


    class BaseViewHolder (itemView: View): RecyclerView.ViewHolder(itemView) {

    }


    internal abstract fun creatHolder(holer: BaseViewHolder, t: T)

    fun setOnrecycleItemClikedListener(listener: OnRecycleItemClickedListener){
        this.listener = listener
    }
}