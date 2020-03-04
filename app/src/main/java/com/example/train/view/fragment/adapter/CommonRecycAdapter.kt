package com.example.train.view.fragment.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView

/**
 * Created by chenyang
 * on 20-3-4
 */
class CommonRecycAdapter<T>(val layoutId: Int, val datas: List<T>, val binHolder: View.(T)->Unit={}): RecyclerView.Adapter<CommonRecycAdapter.SimpleViewHolder>() {

    var context: Context ?= null
    private var itemClick: T.() -> Unit = {}
    constructor(layoutId: Int, datas: List<T>, binHolder: View.(T)->Unit={}, itemClick: T.() ->Unit={}): this(layoutId, datas, binHolder){
        this.itemClick = itemClick
    }


      class SimpleViewHolder(view: View): RecyclerView.ViewHolder(view)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SimpleViewHolder {
        if (context == null) {
            context = parent.context
        }
        val v = layoutId.let { LayoutInflater.from(context).inflate(it,parent,false) }
        return SimpleViewHolder(v)
    }

    override fun getItemCount() = datas.size

     override fun onBindViewHolder(holder: SimpleViewHolder, position: Int) {
         holder.itemView.binHolder(datas.get(holder.adapterPosition))
        holder.itemView.setOnClickListener {  datas.get(position).itemClick()}
    }



}