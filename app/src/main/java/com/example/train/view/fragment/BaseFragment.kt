package com.example.train.view.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.fragment.app.Fragment

/**
 * Created by chenyang
 * on 20-2-27
 */
abstract class BaseFragment: Fragment() {

    @get:LayoutRes
    abstract val contentViewId: Int

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return LayoutInflater.from(context).inflate(contentViewId, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initial(view)
    }

    open fun initial(view: View) = Unit

}