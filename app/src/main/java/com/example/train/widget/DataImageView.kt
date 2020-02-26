package com.example.train.widget

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.util.AttributeSet
import android.widget.ImageView
import androidx.annotation.Nullable

/**
 * Created by chenyang
 * on 20-1-30
 */
class DataImageView: ImageView {
    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    var isShowBorder = false
    var borderColor = Color.GRAY
     var borderWidth = 5f

    var absolutePath = ""
//    var bitMap: Bitmap
    private val paint: Paint

    init {
        paint = Paint(Paint.ANTI_ALIAS_FLAG)
        paint.apply {
            color = borderColor
            strokeWidth = borderWidth
            style = Paint.Style.STROKE
        }
    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)

        if (isShowBorder) {
            val rec = canvas.getClipBounds()
            canvas.drawRect(rec, paint)
        }
    }
}