package com.example.train.view.behavior

import android.content.Context
import android.util.AttributeSet
import android.view.View
import androidx.coordinatorlayout.widget.CoordinatorLayout

/**
 * Created by chenyang
 * on 20-3-12
 */
class MySwipBehavior(context: Context?, attrs: AttributeSet?) : CoordinatorLayout.Behavior<View>(
    context,
    attrs
) {

    override fun layoutDependsOn(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return super.layoutDependsOn(parent, child, dependency)
    }

    override fun onDependentViewChanged(
        parent: CoordinatorLayout,
        child: View,
        dependency: View
    ): Boolean {
        return super.onDependentViewChanged(parent, child, dependency)
    }



}