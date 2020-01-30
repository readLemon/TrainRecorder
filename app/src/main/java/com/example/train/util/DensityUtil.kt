package com.example.train.util

import android.content.res.Resources

/**
 * Created by chenyang
 * on 20-1-30
 */

object DensityUtil {
    val scale = Resources.getSystem().displayMetrics.density

    fun px2dip(pxValue: Int): Int {
        return (pxValue / scale + 0.5f).toInt()
    }

    fun dip2px(dipValue: Int): Int{
        return (dipValue * scale + 0.5f).toInt()
    }

}
