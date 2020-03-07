package com.example.train.widget

import android.annotation.SuppressLint
import android.graphics.Rect
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup
import android.view.animation.Animation
import android.view.animation.TranslateAnimation
import com.mredrock.cyxbs.common.utils.LogUtil

/**
 * 一个让控件上下滑动的TouchListener，后来了解了ViewDragerHlper就没用这个了。想了想还是留着叭，万一后面有用呢
 * Created by chenyang
 * on 20-3-6
 */
class QTouchListener : View.OnTouchListener {

    private lateinit var innerView: View
    private val childrenViewList by lazy { ArrayList<View>() }
    private var y = 0f
    private var isAnimationFinish = true
    private var topsList = ArrayList<Int>()
    private var bottomsList = ArrayList<Int>()
    private val initRect by lazy { Rect() }

    companion object {
        private const val TAG = "QTouchListener"
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        innerView = v
        if (v is ViewGroup) {
            val cnt = v.childCount

            if (cnt > 0) {
                for (i in 0..(cnt - 1)) {
                    childrenViewList.add(i, v.getChildAt(i))
                    topsList.add(i, childrenViewList[i].top)
                    bottomsList.add(i, childrenViewList[i].bottom)
                }
            }
        }
        if (isAnimationFinish) {
            when (event.action) {
                MotionEvent.ACTION_DOWN -> {
                    y = event.y
                    innerView.run { initRect.set(left, top, right, bottom) }
                }

                MotionEvent.ACTION_UP -> {
                    y = 0f
                    if (isNeedAnimation()) {
                        animation()
                    }
                    innerView.invalidate()
                }

                MotionEvent.ACTION_MOVE -> {
                    val preY = if (y == 0f) event.y else y
                    val nowY = event.y
                    val offsetY = preY - nowY
                    if (isNeedMove()) {    //判断是否到了最上或者最下

                        if (childrenViewList.size != 0) {
                            for (tempV in childrenViewList) {
                                tempV.run {
                                    layout(
                                        left,
                                        (tempV.top - offsetY / 2).toInt(),
                                        tempV.right,
                                        (tempV.bottom - offsetY / 2).toInt()
                                    )
                                }
                            }
                            innerView.run {
                                layout(
                                    left,
                                    (top - offsetY / 2).toInt(),
                                    right,
                                    (bottom - offsetY / 2).toInt()
                                )
                            }
                        }
                    }
                    innerView.invalidate()
                }
            }
        } else {
            return false
        }
        return true
    }

    private fun animation() {
        val trans = TranslateAnimation(0f, 0f, 0f, (initRect.top - innerView.top).toFloat())
        trans.duration = 200
        trans.setAnimationListener(object : Animation.AnimationListener {
            override fun onAnimationEnd(animation: Animation?) {
                innerView.clearAnimation()
                initRect.run { innerView.layout(left, top, right, bottom) }
                initRect.setEmpty()
                LogUtil.d(TAG, "动画完成以后的eleevation：${innerView.elevation}")
                isAnimationFinish = true
            }

            override fun onAnimationStart(animation: Animation) {
                isAnimationFinish = false
            }

            override fun onAnimationRepeat(animation: Animation?) {

            }
        })
        innerView.startAnimation(trans)
        if (childrenViewList.size != 0) {
            for (tempV in childrenViewList) {
                if (tempV.visibility == View.VISIBLE) {
                    val index = childrenViewList.indexOf(tempV)
                    val chidTrans =
                        TranslateAnimation(0f, 0f, 0f, (topsList[index] - tempV.top).toFloat())
                    chidTrans.duration = 200
                    chidTrans.setAnimationListener(object : Animation.AnimationListener {

                        override fun onAnimationStart(animation: Animation?) {
                            isAnimationFinish = false
                        }

                        override fun onAnimationEnd(animation: Animation?) {
                            tempV.clearAnimation()
                            tempV.layout(
                                tempV.left,
                                topsList[index],
                                tempV.right,
                                bottomsList[index]
                            )
                            LogUtil.d(TAG, "elevation:")
                            isAnimationFinish = true
                        }

                        override fun onAnimationRepeat(animation: Animation) {

                        }
                    })
                    tempV.startAnimation(chidTrans)
                }
            }
        }
    }

    private fun isNeedAnimation(): Boolean {
        return !initRect.isEmpty
    }

    private fun isNeedMove(): Boolean {
        return true
    }
}