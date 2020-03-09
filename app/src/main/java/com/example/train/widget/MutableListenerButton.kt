package com.example.train.widget

import android.content.Context
import android.util.AttributeSet
import android.view.View.OnClickListener
import androidx.appcompat.widget.AppCompatButton

/**
 * 可以添加多个listener的Button
 * Created by chenyang
 * on 20-3-8
 */
class MutableListenerButton@JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatButton(context, attrs, defStyleAttr) {

    private val listenerList = ArrayList<OnClickListener>()
    private val overrideListener = OnClickListener {
        for (l in listenerList) {
            l.onClick(it)
        }
    }


    fun addListner(linstener: OnClickListener) {
        listenerList.add(linstener)
    }

    fun removeListener(l: OnClickListener) {
        if (listenerList.contains(l)) {
            listenerList.remove(l)
        }
    }

    fun removeAllListener() {
        listenerList.clear()
    }


    override fun setOnClickListener(l: OnClickListener?) {
        if (l != null) {
            if (!listenerList.contains(l)) {
                listenerList.add(l)
            }
        }
        super.setOnClickListener(overrideListener)
    }


}