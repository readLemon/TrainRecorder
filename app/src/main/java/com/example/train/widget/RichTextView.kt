package com.example.train.widget

import android.animation.LayoutTransition
import android.content.Context
import android.graphics.Color
import android.text.TextUtils
import android.util.AttributeSet
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.example.train.R
import com.example.train.util.DensityUtil
import com.example.train.widget.interfaces.OnRTImageViewClickListener


/**
 * Created by chenyang
 * on 20-2-7
 */
class RichTextView(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int
) : ScrollView(context, attrs, defStyleAttr) {

    companion object {
        private val EDIT_PADDING = 10
        private val DEFAULT_TEXT_COLOR = Color.GRAY
    }

    private var viewTagIndex = 1
    private val baseLayout: LinearLayout
    private val inflater: LayoutInflater

    private var lastFocusText: TextView
    private var editNormalPadding = 0

    private var mOnRTImageViewClickListener: OnRTImageViewClickListener? = null
    private var mImageClickListener: OnClickListener
    private val imagePathes = ArrayList<String>()

    private var keyWords = ""
    var textLightColor = Color.parseColor("#EE5C42")


    /** 自定义属性  */

    //插入的图片显示高度
    private var rtImageHeight = 0 //为0显示原始高度

    //两张相邻图片间距
    private var rtImageBottom = 0
    //文字相关属性，初始提示信息，文字大小和颜色
    private var rtTextHint = "没有内容"
    //getResources().getDimensionPixelSize(R.dimen.text_size_16)
    private var rtTextSize = 16 //相当于16sp

    private var rtTextColor: Int = Color.parseColor("#757575")
    private var rtTextLineSpace = 8 //相当于8dp


    init {
        if (context != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.RichTextView)
            rtImageHeight = ta.getInteger(R.styleable.RichTextView_image_height, 0)
            rtImageBottom = ta.getInteger(R.styleable.RichTextView_image_bottom, 10)
            ta.getString(R.styleable.RichTextView_text_view_hint).apply {
                if (this != null) {
                    rtTextHint = this
                }
            }
            rtTextSize = ta.getInteger(R.styleable.RichTextView_text_size, 16)
            rtTextColor = ta.getColor(R.styleable.RichTextView_text_color, DEFAULT_TEXT_COLOR)
            rtTextLineSpace = ta.getDimensionPixelSize(R.styleable.RichTextView_text_line_space, 8)
            ta.recycle()
        }
        val params = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT)
        inflater = LayoutInflater.from(context)
        baseLayout = LinearLayout(context)
        baseLayout.apply {
            orientation = LinearLayout.VERTICAL
            setPadding(50, 15, 50, 15)
        }
        addView(baseLayout, params)

        mImageClickListener = OnClickListener{
            if (it is DataImageView) {
                if (mOnRTImageViewClickListener != null) {
                    (mOnRTImageViewClickListener as OnRTImageViewClickListener).onImageClick(it, it.absolutePath)
                }
            }
        }
        val firstTextView = creatTextView(DensityUtil.dip2px(EDIT_PADDING))
        baseLayout.addView(firstTextView, params)
        lastFocusText = firstTextView
    }

    private fun creatTextView(paddingTop: Int): TextView {
        val textView = inflater.inflate(R.layout.rich_text_view, null) as TextView
        textView.apply {
            tag = viewTagIndex++
            setPadding(editNormalPadding, paddingTop, editNormalPadding, paddingTop)
            setTextSize(TypedValue.COMPLEX_UNIT_PX, rtTextSize.toFloat())
            setTextColor(rtTextColor)
            setLineSpacing(rtTextLineSpace.toFloat(), 1.0f)
        }

        return textView
    }


     fun createImageLayout(): RelativeLayout {
        val layout = inflater.inflate(R.layout.rich_editor_image_view, null) as RelativeLayout
        layout.tag = viewTagIndex++
        val close = layout.findViewById<ImageView>(R.id.rich_edit_image_delete)
        close.tag = layout.tag
        close.visibility = View.INVISIBLE
        layout.findViewById<DataImageView>(R.id.rich_edit_image_view)
            .setOnClickListener(mImageClickListener)
        return layout
    }

    fun addTextViewAtIndex(index: Int, textStr: String) {
        val textView = creatTextView( EDIT_PADDING)
        if (!TextUtils.isEmpty(keyWords)) {
            val str = RichEditor.highlight(textStr, keyWords, textLightColor)
            textView.text = str
        } else {
            textView.setText(textStr)
        }

        baseLayout.addView(textView, index)
    }

    fun addImageViewAtIndex(index: Int, imagePath: String) {
        if (TextUtils.isEmpty(imagePath)) {
            return
        }
        imagePathes.add(imagePath)
        val imageLayout = createImageLayout()
        val imageView = imageLayout.findViewById<DataImageView>(R.id.rich_edit_image_view)
        RichEditorImageLoader.getSingleton()?.loadImage(imageView, imagePath, rtImageHeight)
        baseLayout.addView(imageLayout, index)
    }

    fun clearAllLayout() {
        baseLayout.removeAllViews()
    }

    fun setOnRTImageViewClickListener(listener: OnRTImageViewClickListener) {
        mOnRTImageViewClickListener = listener
    }

}