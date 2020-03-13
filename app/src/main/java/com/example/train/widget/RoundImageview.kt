package com.example.train.widget

import android.content.Context
import android.graphics.*
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.util.AttributeSet
import androidx.appcompat.widget.AppCompatImageView
import kotlin.properties.Delegates

/**
 * Created by chenyang
 * on 20-3-13
 */
class RoundImageview @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : AppCompatImageView(context, attrs, defStyleAttr) {
    //
    private val paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private val paintBorder by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }
    private var srcBitmap by Delegates.notNull<Bitmap>()

    private val xfermode by lazy { PorterDuffXfermode(PorterDuff.Mode.SRC_IN) }

    private val mRadius = 40f
    private var isCircle = false


    init {
        if (attrs != null) {

            val srcResources = attrs.getAttributeResourceValue(
                "http://schemas.android.com/apk/res/android",
                "src",
                0
            )
            if (srcResources != 0) {
                srcBitmap = BitmapFactory.decodeResource(resources, srcResources)
            }

        }
    }

    override fun onDraw(canvas: Canvas) {
        val mWidth = width - paddingLeft - paddingRight
        val mHeight = height - paddingTop - paddingBottom
        val image = drawable2Bitmap(drawable)

        if (isCircle) {
            val resizedBitmap = resizeCircleBitmap(image, mWidth, mHeight)
            val circleImage = creatCircleImage(resizedBitmap, mWidth, mHeight)
            canvas.drawBitmap(circleImage, paddingLeft.toFloat(), paddingRight.toFloat(), null)
        } else {
            val resizedBitmap = resizeRoundBitmap(image, mWidth, mHeight)
            val roundImage = creatRoundImage(resizedBitmap, mWidth, mHeight)
            canvas.drawBitmap(roundImage, paddingLeft.toFloat(), paddingTop.toFloat(), null)
        }
    }

    private fun resizeCircleBitmap(image: Bitmap, mWidth: Int, mHeight: Int): Bitmap {
        val w = image.width
        val h = image.height
        val x= (mWidth - w) / 2
        val y = (mHeight - h) / 2
        if (x > 0 && y > 0) {
            return Bitmap.createBitmap(image,0,0,mWidth,mHeight, null, true)
        }
        val scale: Float
        if (w > h) {
            scale = mWidth.toFloat() / w
        } else {
            scale = mHeight.toFloat() / h
        }
        val matrix = Matrix()
        matrix.postScale(scale, scale)
        return Bitmap.createBitmap(image,0,0,mWidth,mHeight,matrix,true)
    }

    private fun creatRoundImage(sourceBitmap: Bitmap, mWidth: Int, mHeight: Int): Bitmap {
        val target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(target)
        val rectF = RectF(0f,0f, mWidth.toFloat(), mHeight.toFloat())
        paint.xfermode = null
        canvas.drawRoundRect(rectF, mRadius, mRadius, paint)
        paint.xfermode = xfermode
        canvas.drawBitmap(sourceBitmap, 0F,0F,paint)
        return target
    }

    private fun creatCircleImage(sourceBitmap: Bitmap, mWidth: Int, mHeight: Int): Bitmap {
        val target = Bitmap.createBitmap(mWidth, mHeight, Bitmap.Config.ARGB_8888)
        val canvas = Canvas(target)
        paint.xfermode = null
        canvas.drawCircle(
            (mWidth / 2).toFloat(),
            (mHeight / 2).toFloat(),
            (mWidth / 2).toFloat(),
            paint
        )
        paint.xfermode = xfermode
        canvas.drawBitmap(
            sourceBitmap,
            ((mWidth - sourceBitmap.width) / 2).toFloat(),
            ((mHeight - sourceBitmap.height) / 2).toFloat(),
            paint
        )
        return target
    }

    private fun resizeRoundBitmap(image: Bitmap, width: Int, height: Int): Bitmap {
        val w = image.width
        val h = image.height
        val sw = width.toFloat() / w
        val sh = height.toFloat() / h
        val matrix = Matrix()
        matrix.postScale(sw, sh)
        return Bitmap.createBitmap(image, 0, 0, w, h, matrix, true)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)
        if (isCircle) {
            val result = Math.min(measuredWidth, measuredHeight)
            setMeasuredDimension(result, result)
        }
    }

    private fun drawable2Bitmap(drawable: Drawable?): Bitmap {
        if (drawable == null) {
            return srcBitmap
        } else if (drawable is BitmapDrawable) {
            return drawable.bitmap
        }
        val bitmap = Bitmap.createBitmap(
            drawable.intrinsicWidth,
            drawable.intrinsicHeight,
            Bitmap.Config.ARGB_8888
        )
        val canvas = Canvas(bitmap)
        drawable.setBounds(0, 0, canvas.width, canvas.height)
        drawable.draw(canvas)
        return bitmap
    }
}