package com.example.train.widget

import android.animation.Animator
import android.animation.ObjectAnimator
import android.animation.ValueAnimator
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.Build
import android.util.AttributeSet
import android.view.View
import android.view.animation.LinearInterpolator
import androidx.annotation.Nullable
import androidx.annotation.RequiresApi
import com.example.train.R
import com.mredrock.cyxbs.common.utils.LogUtil
import kotlin.properties.Delegates

/**
 * 留了一个setData接口出来，用于输入数据
 *
 * Created by chenyang
 * on 20-1-23
 */
@RequiresApi(Build.VERSION_CODES.N)
class LineChartView @JvmOverloads constructor(
    context: Context,
    @Nullable attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : View(context, attrs, defStyleAttr) {


    private val textSize = 50f
    private var mPadding = 50f
    private val monthPerYear = 12
    private val mChartPath: Path

    private val chartData = arrayListOf<Float>()
    private var mWidth = -1
    private var mHeight = -1
    private val mChartBgPaint: Paint
    private val mPathPaint: Paint
    private val canvasPaint: Paint by lazy { Paint(Paint.ANTI_ALIAS_FLAG) }

    private var radius by Delegates.notNull<Int>()
    private var radiusTopLeft by Delegates.notNull<Int>()
    private var radiusTopRight by Delegates.notNull<Int>()
    private var radiusBottomRight by Delegates.notNull<Int>()
    private var radiusBottomLeft by Delegates.notNull<Int>()


    val paintColor: Int = Color.BLUE
    val backGroundColor = Color.WHITE
    val chartPathColor = Color.GREEN
    val pathWidth = 6F

    private var mAnimProgress = 0f

    init {
        mPadding = mPadding + paddingLeft
        mPathPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mPathPaint.apply {
            style = Paint.Style.STROKE
            strokeWidth = pathWidth
            strokeCap = Paint.Cap.ROUND
            color = chartPathColor
            strokeJoin = Paint.Join.ROUND
        }
        mChartBgPaint = Paint(Paint.ANTI_ALIAS_FLAG)
        mChartBgPaint.textSize = textSize
        mChartPath = Path()
        mChartBgPaint.strokeWidth = 2f
        mChartBgPaint.color = paintColor
        initMyAttr(attrs)
//        setBackgroundColor(myGrad.shape)
        setData()
        canvasPaint
        canvasPaint.apply {
            style = Paint.Style.FILL
            color = backGroundColor
        }
    }

    private fun initMyAttr(attrs: AttributeSet?) {
        val ta = context.obtainStyledAttributes(attrs, R.styleable.LineChartView)
        radius = ta.getColor(R.styleable.LineChartView_line_chart_radius, 10)
        //写了，没有用到。懒得删了
        radiusTopLeft = ta.getColor(R.styleable.LineChartView_line_chart_top_left_radius, radius)
        radiusTopRight = ta.getColor(R.styleable.LineChartView_line_chart_top_right_radius, radius)
        radiusBottomRight =
            ta.getColor(R.styleable.LineChartView_line_chart_bottom_right_radius, radius)
        radiusBottomLeft =
            ta.getColor(R.styleable.LineChartView_line_chart_bottom_left_radius, radius)

        ta.recycle()

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

    @SuppressLint("DrawAllocation")
    override fun onDraw(canvas: Canvas) {
        super.onDraw(canvas)
        val recf = RectF(0f,0f, measuredWidth.toFloat(), measuredHeight.toFloat())
        canvas.drawRoundRect(recf, radius.toFloat(), radius.toFloat(),canvasPaint)
        mWidth = width
        mHeight = height

        //画底下和顶上的线
        drawX(canvas)

        //画左边的标尺线
        drawY(canvas)

//        //画下边的标尺线
        drawBottomLineAndText(canvas)
//
//        //绘制上面的文字提示
//        drawTopText(canvas)

        drawLine(canvas)

        setAnim(canvas)

    }

    private fun setAnim(canvas: Canvas) {
        val measure = PathMeasure(mChartPath, false)
        val pathLength = measure.length
        val effect = DashPathEffect(floatArrayOf(pathLength, pathLength), pathLength - pathLength * mAnimProgress)
        mChartBgPaint.pathEffect = effect
        canvas.drawPath(mChartPath, mPathPaint)
    }

    @SuppressLint("AnimatorKeep")
    //这个要用反射，暂时先不处理
    fun startAnim(lineChartView: LineChartView,duration: Long) {
        val animator = ObjectAnimator.ofFloat(lineChartView,"progress",0f,1f)
        animator.duration = duration
        animator.interpolator = LinearInterpolator()
        animator.start()
    }

//    fun startAnim(duration: Long) {
//        val animator = ValueAnimator.ofFloat(0f,1f)
//        animator.duration = duration
//        animator.interpolator = LinearInterpolator()
//        animator.addUpdateListener {
//            setProgress(it.animatedValue as Float)
//        }
//    }

    private fun setProgress(progress: Float) {
        if(progress < 0f || progress > 1f) {
            throw IllegalAccessException("动画的进度出现问题")
        }
        mAnimProgress = progress
        invalidate()
    }

    private fun drawLine(canvas: Canvas) {
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
                LogUtil.d("LineChartView", e.message.toString())
            }
        }
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
        mChartBgPaint.style = Paint.Style.FILL
        canvas.drawLine(
            mPadding,
            0f,
            mPadding,
            mHeight - mPadding,
            mChartBgPaint
        )

        val tempY = (mHeight - mPadding * 2) / 6
        for (i in 1..7) {
            canvas.drawText("${i}", 10f, (mHeight - mPadding - i * tempY), mChartBgPaint)
            canvas.drawLine(
                mPadding, (mHeight - mPadding - i * tempY),
                mWidth.toFloat(), (mHeight - mPadding - i * tempY), mChartBgPaint
            )
        }
    }

    private fun drawX(canvas: Canvas) {
        canvas.drawLine(
            mPadding,
            mHeight - mPadding,
            mWidth.toFloat(),
            mHeight - mPadding,
            mChartBgPaint
        )
        val tempX = (mWidth - mPadding * 2) / (monthPerYear - 1)
        for (i in 1..monthPerYear) {
            canvas.drawText(
                "${monthPerYear - i + 1}",
                mWidth - mPadding - (i - 1) * tempX,
                mHeight.toFloat(),
                mChartBgPaint
            )

        }
    }

    private fun setData(chartData: List<Float>) {
        if (chartData.size != 0) {
            this.chartData.clear()
            this.chartData.addAll(chartData)
        }
    }


    /**
     * 测试用数据
     */
    private fun setData() {
        chartData.addAll(
            listOf(
                1f, 2f, 3f, 1f,
                4f, 3f, 3f, 2f,
                1f, 4f, 5f, 4f
            )
        )
//        chartData.addAll(listOf(1f,2f,3f,1f))
    }

    override fun onSizeChanged(w: Int, h: Int, oldw: Int, oldh: Int) {
        super.onSizeChanged(w, h, oldw, oldh)
        mWidth = width
        mHeight = height
    }
}