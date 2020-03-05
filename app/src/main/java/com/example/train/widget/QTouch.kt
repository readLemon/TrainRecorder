package com.example.train.widget

import android.annotation.SuppressLint
import android.view.MotionEvent
import android.view.View
import android.view.ViewGroup

/**
 * Created by chenyang
 * on 20-3-6
 */
class QTouch : View.OnTouchListener {

    private var startX = 0
    private var startY = 0
    private var moveX = 0
    private var moveY = 0
    private var offsetX = 0
    private var offsetY = 0

    @SuppressLint("ClickableViewAccessibility")
    override fun onTouch(v: View, event: MotionEvent): Boolean {
        moveX = event.x.toInt()
        moveY = event.y.toInt()

        when (event.action) {
            MotionEvent.ACTION_DOWN -> {
                startX = moveX
                startY = moveY
            }

            MotionEvent.ACTION_MOVE -> {
                 offsetX = moveX - startX
                 offsetY = moveY - startY

                v.layout(
                    v.left + offsetX,
                    v.top + offsetY,
                    v.right + offsetX,
                    v.bottom + offsetY
                )
                v.requestLayout()
            }

            MotionEvent.ACTION_UP -> {
                v.layout(v.left - offsetX,
                    v.top - offsetY,
                    v.right - offsetX,
                    v.bottom - offsetY)
            }


        }
        return false
    }
}