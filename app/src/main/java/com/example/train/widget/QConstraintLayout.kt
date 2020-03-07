package com.example.train.widget

import android.annotation.SuppressLint
import android.content.Context
import android.util.AttributeSet
import android.view.MotionEvent
import android.view.View
import androidx.cardview.widget.CardView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.customview.widget.ViewDragHelper
import com.mredrock.cyxbs.common.utils.LogUtil

/**
 * Created by chenyang
 * on 20-3-6
 */
class QConstraintLayout @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ConstraintLayout(context, attrs, defStyleAttr) {

    private val mDragHelper: ViewDragHelper
    private var initLeft = 0
    private var initTop = 0

    init {
        mDragHelper = ViewDragHelper.create(this, MyDrageCallBack())
    }

    override fun onInterceptTouchEvent(ev: MotionEvent): Boolean {
        return mDragHelper.shouldInterceptTouchEvent(ev)
    }

    companion object {
        private val TAG = "QConstraintLayout"
    }

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouchEvent(event: MotionEvent): Boolean {
        mDragHelper.processTouchEvent(event)
        if (event.action == MotionEvent.ACTION_DOWN) {
            LogUtil.d(TAG, "初始化initLeft = ${left}, initTop = ${top}")
        }
        return true
    }

    override fun computeScroll() {
        super.computeScroll()
        if (mDragHelper.continueSettling(true)) {
            invalidate()
        }
    }


    inner class MyDrageCallBack : ViewDragHelper.Callback() {

        override fun getViewHorizontalDragRange(child: View): Int {
            return measuredWidth - child.measuredWidth
        }

        override fun getViewVerticalDragRange(child: View): Int {
            return measuredHeight - child.measuredHeight
        }

        override fun tryCaptureView(child: View, pointerId: Int): Boolean {
            initLeft = child.left
            initTop = child.top
            return child is CardView
        }

        /**
         * 被拖拽时的回调
         * @param changedView   被拖拽的view
         * @param left          被拖拽后view的left边缘
         * @param top           被拖拽后view的top边缘
         * @param dx            x偏移量
         * @param dy            y偏移量
         */
        override fun onViewPositionChanged(
            changedView: View,
            left: Int,
            top: Int,
            dx: Int,
            dy: Int
        ) {

        }

        /**
         * view被释放
         * @param xvel x轴速度
         * @param yvel y轴速度
         */
        override fun onViewReleased(releasedChild: View, xvel: Float, yvel: Float) {
            mDragHelper.settleCapturedViewAt(initLeft, initTop)
            invalidate()
            initTop = 0
            initLeft = 0
        }


        /**
         * 决定拖拽View在水平方向上应该移动到的位置
         * @param child  被拖拽的View
         * @param left  期望移动到位置的View的left值
         * @param dx  移动的速度
         * @return 直接决定View在水平方向的位置
         */
        override fun clampViewPositionHorizontal(child: View, left: Int, dx: Int): Int {
            val leftBound = paddingLeft
            val rightBound = width - child.width - paddingRight
            return Math.min(Math.max(left, leftBound), rightBound)
        }

        override fun clampViewPositionVertical(child: View, top: Int, dy: Int): Int {
            val topBound = paddingTop
            val bottomBound = height - child.height - paddingBottom
            return Math.min(Math.max(top, topBound), bottomBound)

        }

    }
}