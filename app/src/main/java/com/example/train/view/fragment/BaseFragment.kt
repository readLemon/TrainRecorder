package com.example.train.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.Observer
import com.example.train.App

/**
 * Created by chenyang
 * on 20-2-27
 */
abstract class BaseFragment: Fragment() {

    @get:LayoutRes
    abstract val contentViewId: Int


    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        return LayoutInflater.from(context).inflate(contentViewId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial(view)
    }

    open fun initial(view: View) = Unit

    //嫖的袁兵大哥写的
    inline fun <T> LiveData<T>.observeNotNull(crossinline onChange: (T) -> Unit) = observe(this@BaseFragment, Observer {
        it ?: return@Observer
        onChange(it)
    })

    fun showToast(msg: String) {
        Toast.makeText(context,msg, Toast.LENGTH_SHORT).show()
    }

}