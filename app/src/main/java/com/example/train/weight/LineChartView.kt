package com.example.train.weight

import android.content.Context
import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.Path
import android.util.AttributeSet
import android.view.View
import androidx.annotation.Nullable
import com.mredrock.cyxbs.common.utils.LogUtils

/**
 * Created by chenyang
 * on 20-1-23
 */
class LineChartView : View {
    constructor(context: Context) : super(context)
    constructor(context: Context, @Nullable attrs: AttributeSet) : super(context, attrs)
    constructor(context: Context, @Nullable attrs: AttributeSet, defStyleAttr: Int) : super(
        context,
        attrs,
        defStyleAttr
    )

    private val textSize = 50f
    private val mPadding = 50f
    private val monthPerYear = 12
    private val mChartPath: Path

    private val chartData = arrayListOf<Float>()
    private var mWidth = -1
    private var mHeight = -1
    private val mPaint: Paint
    private val mPathPaint: Paint


    val paintColor: Int = Color.BLUE
    val backGroundColor = Color.WHITE
    val charPathColor = Color.GREEN


    init {
        mPathPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPathPaint.apply {
            style = Paint.Style.STROKE
            strokeWidth = 20F
            strokeCap = Paint.Cap.ROUND
            color = charPathColor
        }
        mPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPaint.textSize = textSize
        mChartPath = Path()
        mPaint.strokeWidth = 2f
        mPaint.color = paintColor
        setBackgroundColor(backGroundColor)
    }

    override fun onMeasure(widthMeasureSpec: Int, heightMeasureSpec: Int) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec)

        val widthSpecMode = MeasureSpec.getMode(widthMeasureSpec)
        val widthSpecSize = MeasureSpec.getSize(widthMeasureSpec)
        val heightSpecMode = MeasureSpec.getMode(heightMeasureSpec)
        val heightSpecSize = MeasureSpec.getSize(heightMeasureSpec)

        if (widthSpecMode == MeasureSpec.AT_MOST &&
            heightSpecMode == MeasureSpec.AT_MOST
        ) {
            setMeasuredDimension(400, 600)
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(400, heightSpecSize)
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, 600)
        }

    }

    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        mWidth = width
        mHeight = height

        setData()
        //画底下和顶上的线
        drawX(canvas)

        //画左边的标尺线
        drawY(canvas)

//        //画下边的标尺线
        drawBottomLineAndText(canvas)
//
//        //绘制上面的文字提示
//        drawTopText(canvas)

        drawChart(canvas)
    }

    private fun drawChart(canvas: Canvas) {
        val tempY = (mHeight - mPadding * 2) / 6 //Y轴间隔
        val tempX = (mWidth - mPadding * 2) / (monthPerYear - 1) //x轴间隔
        var temp = 0f
        var x = 0f
        var y = 0f
        mChartPath.reset()
        if (chartData.size != 0) {
            temp = chartData.get(0) * tempY + mPadding
            mChartPath.moveTo(mPadding, mHeight - temp)
            try {
                for (i in 1..(chartData.size - 1)) {
                    x = i * tempX + mPadding
                    y = tempY * chartData.get(i) + mPadding
                    mChartPath.lineTo(x, y)
                }

            } catch (e: ArrayIndexOutOfBoundsException) {
                LogUtils.d("LineChartView", e.message.toString())
            }
        }
        canvas.drawPath(mChartPath, mPathPaint)
    }

    private fun drawBottomLineAndText(canvas: Canvas) {

    }

    /**
     * 左边距离View边界mPadding
     * 距离上下边界各mPadding的长度
     * @param null
     * @return
     */
    private fun drawY(canvas: Canvas) {
        mPaint.style = Paint.Style.FILL
        canvas.drawLine(
            mPadding,
            0f,
            mPadding,
            mHeight - mPadding,
            mPaint
        )

        val tempY = (mHeight - mPadding * 2) / 6
        for (i in 1..7) {
            canvas.drawText("${i}", 10f, (mHeight - mPadding - i * tempY), mPaint)
            canvas.drawLine(
                mPadding, (mHeight - mPadding - i * tempY),
                mWidth.toFloat(), (mHeight - mPadding - i * tempY), mPaint
            )
        }
    }

    private fun drawX(canvas: Canvas) {
        LogUtils.d("*****", "${height}*****${width}")
        canvas.drawLine(mPadding, mHeight - mPadding, mWidth.toFloat(), mHeight - mPadding, mPaint)
        val tempX = (mWidth - mPadding * 2) / (monthPerYear - 1)
        for (i in 1..monthPerYear) {
            canvas.drawText(
                "${monthPerYear - i + 1}",
                mWidth - mPadding - (i-1) * tempX,
                mHeight.toFloat(),
                mPaint
            )

        }
    }

    private fun setData(chartData: List<Float>) {
        if (chartData.size != 0) {
            this.chartData.clear()
            this.chartData.addAll(chartData)
        }
    }


    private fun setData() {
        chartData.addAll(listOf(1f,2f,3f,1f,
            4f,3f,3f,2f,
            1f,4f,5f,4f))
//        chartData.addAll(listOf(1f,2f,3f,1f))
    }
    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = width
        mHeight = height
    }
}