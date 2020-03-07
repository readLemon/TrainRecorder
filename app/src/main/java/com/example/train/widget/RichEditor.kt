package com.example.train.widget

import android.animation.LayoutTransition
import android.annotation.SuppressLint
import android.content.Context
import android.graphics.Bitmap
import android.graphics.Color
import android.graphics.drawable.Drawable
import android.text.Spannable
import android.text.SpannableStringBuilder
import android.text.TextUtils
import android.text.style.CharacterStyle
import android.text.style.ForegroundColorSpan
import android.util.AttributeSet
import android.util.TypedValue
import android.view.KeyEvent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.*
import androidx.annotation.ColorInt
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.example.train.R
import com.example.train.util.DensityUtil
import com.example.train.widget.interfaces.IRichEditorImageLoader
import com.example.train.widget.interfaces.OnImageDeleteClickListener
import com.example.train.widget.interfaces.OnREImageViewClickListener
import com.mredrock.cyxbs.common.utils.LogUtil
import java.util.regex.Pattern

/**
 * Created by chenyang
 * on 20-1-29
 */
class RichEditor @JvmOverloads constructor(
    context: Context?,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : ScrollView(context, attrs, defStyleAttr) {


    companion object {
        val EDIT_PADDING = 10
        val imageBottomMargin = 10 //图片的底边距

        /**
         * @param text 需要显示的文字
         * @param target 需要高亮的关键字
         */
         fun highlight(text: String, target: String, @ColorInt textLightColor: Int): SpannableStringBuilder {
            val spannable = SpannableStringBuilder(text)
            var span: CharacterStyle

            val p = Pattern.compile(target)
            val m = p.matcher(text)
            while (m.find()) {
                span = ForegroundColorSpan(textLightColor)
                spannable.setSpan(span, m.start(), m.end(), Spannable.SPAN_EXCLUSIVE_EXCLUSIVE)
            }

            return spannable
        }
    }

    var textLightColor = Color.parseColor("#EE5C42")
    var keywords = ""
    var mImageClickListener: OnREImageViewClickListener? = null //开放图片点击接口


    private val mKeyListener: OnKeyListener
    private var mOnImageDeleteClickListener: OnImageDeleteClickListener? = null
    private val mBtnListener: OnClickListener
    private var mFocusListener: OnFocusChangeListener? = null

    private var viewTagIndex = 1
    private val baseLayout: LinearLayout
    private val inflater: LayoutInflater
    private val mTransitioner: LayoutTransition

    private val imagePaths: ArrayList<String>

    private var lastFocusEdit: EditText
    private var editNormalPadding = 0
    private var disappearingImageIndex = 0

    private var reImageHeight: Int = 0
    private var reImageSpace: Int = 0
    private var reInitHont: String = "请输入"
    private var reTextSize = 16f  //sp
    private var reTextColor = Color.parseColor("#757575")
    private var reTextLineSpace = 8 //dp

    init {
        if (context != null) {
            val ta = context.obtainStyledAttributes(attrs, R.styleable.RichEditor)
            reImageHeight = ta.getInteger(R.styleable.RichEditor_image_height, 0)
            reImageSpace = ta.getInteger(R.styleable.RichEditor_image_space, 10)
            reTextSize = ta.getInteger(R.styleable.RichEditor_text_size, 16).toFloat()
            ta.getString(R.styleable.RichEditor_text_init_hint).apply {
                if (this != null) {
                    reInitHont = this
                }
            }
            reTextColor =
                ta.getColor(R.styleable.RichEditor_text_color, Color.parseColor("#757575"))
            ta.recycle()
        }

        initImageLoader()

        imagePaths = ArrayList()
        inflater = LayoutInflater.from(context)
        mTransitioner = LayoutTransition()
        val params = LinearLayout.LayoutParams(
            ViewGroup.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        baseLayout = LinearLayout(context)
        setupLayoutTransition()
        baseLayout.orientation = LinearLayout.VERTICAL
        baseLayout.setPadding(50, 15, 50, 15)
        addView(baseLayout, params)
        mKeyListener = object : OnKeyListener {
            override fun onKey(v: View, keyCode: Int, e: KeyEvent): Boolean {
                if (e.action == KeyEvent.ACTION_DOWN
                    && e.keyCode == KeyEvent.KEYCODE_DEL
                ) {
                    onBackspacePress(v as EditText)
                }

                return false
            }
        }

        mBtnListener = object : OnClickListener {
            override fun onClick(v: View) {
                if (v is DataImageView) {
                    if (mImageClickListener != null) {
                        (mImageClickListener as OnREImageViewClickListener).onImageClick(
                            v,
                            v.absolutePath
                        )
                    }
                } else if (v is ImageView) {
                    onImageCloseClick(v.parent as RelativeLayout)
                }
            }
        }

        mFocusListener = object : OnFocusChangeListener {
            override fun onFocusChange(v: View, hasFocus: Boolean) {
                if (hasFocus) {
                    lastFocusEdit = v as EditText
                }
            }
        }

        val firstEditParam = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            ViewGroup.LayoutParams.MATCH_PARENT
        )
        val firstText = createEditText(reInitHont, DensityUtil.dip2px(EDIT_PADDING))
        lastFocusEdit = firstText
        baseLayout.addView(firstText, firstEditParam)
    }


    fun insertImage(imagePath: String) {
        if (TextUtils.isEmpty(imagePath)) {
            return
        }
        val lastFocusEditStr = lastFocusEdit.text.toString()
        val cursorIndex = lastFocusEdit.selectionStart
        val strBeforeCur = lastFocusEditStr.substring(0, cursorIndex).trim()
        val strAfterCur = lastFocusEditStr.substring(cursorIndex).trim()
        val focusEditIndex = baseLayout.indexOfChild(lastFocusEdit)

        if (lastFocusEditStr.length == 0) {
            //如果当前获取焦点的EditText为空，直接在EditText下方插入图片，并且插入空的EditText
            addEditAtIndex(focusEditIndex + 1, "")
            addImageViewAtIndex(focusEditIndex + 1, imagePath)
        } else if (strBeforeCur.length == 0) {
            //如果光标已经顶在了editText的最前面，则直接插入图片，并且EditText下移即可
            addImageViewAtIndex(focusEditIndex, imagePath)
            //同时插入一个空的EditText，防止插入多张图片无法写文字
            addEditAtIndex(focusEditIndex + 1, "")
        } else if (strAfterCur.length == 0) {
            // 如果光标已经顶在了editText的最末端，则需要添加新的imageView和EditText
            addEditAtIndex(focusEditIndex + 1, "")
            addImageViewAtIndex(focusEditIndex + 1, imagePath)
        } else {
            //如果光标已经顶在了editText的最中间，则需要分割字符串，分割成两个EditText，并在两个EditText中间插入图片
            //把光标前面的字符串保留，设置给当前获得焦点的EditText（此为分割出来的第一个EditText）
            lastFocusEdit.setText(strBeforeCur)
            //把光标后面的字符串放在新创建的EditText中（此为分割出来的第二个EditText）
            addEditAtIndex(focusEditIndex + 1, strAfterCur)
            //在第二个EditText的位置插入一个空的EditText，以便连续插入多张图片时，有空间写文字，第二个EditText下移
            addEditAtIndex(focusEditIndex + 1, "")
            //在空的EditText的位置插入图片布局，空的EditText下移
            addImageViewAtIndex(focusEditIndex + 1, imagePath)
        }
        hideKeyBoard()
    }

    /**
     * 隐藏小键盘
     */
    private fun hideKeyBoard() {
        val imm = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        imm.hideSoftInputFromWindow(lastFocusEdit.windowToken, 0)
    }

    /**
     * 在指定位置添加ImageView
     */
    private fun addImageViewAtIndex(index: Int, imagePath: String) {
        if (TextUtils.isEmpty(imagePath)) {
            return
        }
        imagePaths.add(imagePath)
        val imageLayout = createImageLayout()
        val dataImageView = imageLayout.findViewById<DataImageView>(R.id.rich_edit_image_view)
        dataImageView.absolutePath = imagePath
        dataImageView.scaleType = ImageView.ScaleType.CENTER_CROP //裁剪剧中
        RichEditorImageLoader.getSingleton()?.loadImage(dataImageView, imagePath, reImageHeight)
        baseLayout.addView(imageLayout, index)
    }

    private fun createImageLayout(): RelativeLayout {
        val layout = inflater.inflate(R.layout.rich_editor_image_view, null) as RelativeLayout
        layout.tag = viewTagIndex++
        val close = layout.findViewById<ImageView>(R.id.rich_edit_image_delete)
        close.tag = layout.tag
        close.setOnClickListener(mBtnListener)
        layout.findViewById<DataImageView>(R.id.rich_edit_image_view)
            .setOnClickListener(mBtnListener)
        return layout
    }


    /**
     * 在特定位置插入EditText
     */
    private fun addEditAtIndex(index: Int, editStr: String) {
        val newEditText = createEditText("插入文字", EDIT_PADDING)
        if (!TextUtils.isEmpty(keywords)) {
            val textStr = highlight(editStr, keywords, textLightColor)
            newEditText.text = textStr
        } else if (!TextUtils.isEmpty(editStr)) {
            newEditText.setText(editStr)
        }
        newEditText.onFocusChangeListener = mFocusListener

        baseLayout.apply {
            layoutTransition = null
            addView(newEditText, index)
            layoutTransition = mTransitioner
        }
        lastFocusEdit = newEditText
        lastFocusEdit.requestFocus()
        lastFocusEdit.setSelection(editStr.length, editStr.length)
    }




    /**
     * 处理键盘的回退事件
     * @param null
     * @return
     */
    @SuppressLint("SetTextI18n")
    private fun onBackspacePress(e: EditText) {
        val startSelection = e.selectionStart
        //只有在光标已经顶到文本输入框的最前方，在判定是否删除之前的图片，或两个View合并
        if (e.selectionStart == 0) {
            val editIndex = baseLayout.indexOfChild(e)
            val preView = baseLayout.getChildAt(editIndex - 1)

            if (preView != null) {
                if (preView is RelativeLayout) {
                    onImageCloseClick(preView)
                } else if (preView is EditText) {
                    val str1 = preView.text.toString()
                    val str2 = e.text.toString()

                    baseLayout.apply {
                        layoutTransition = null
                        removeView(e)
                        layoutTransition = mTransitioner
                    }

                    preView.apply {
                        setText(str1 + str2)
                        requestFocus()
                        setSelection(str1.length, str2.length)
                    }

                    lastFocusEdit = preView
                }
            }
        }
    }

    /**
     * 处理图片叉掉的点击事件
     * @param null
     * @return
     */
    private fun onImageCloseClick(v: View) {
        if (!mTransitioner.isRunning) {
            disappearingImageIndex = baseLayout.indexOfChild(v)
            val dataList = buidldData()
            val editData = dataList.get(disappearingImageIndex)
            if (editData.imagePath != null) {
                if (mOnImageDeleteClickListener != null) {
                    editData.imagePath?.let {
                        (mOnImageDeleteClickListener as OnImageDeleteClickListener)
                            .onDeleteImageClick(it)
                    }
                }
                imagePaths.remove((editData as EditData).imagePath)
            }

            baseLayout.removeView(v)
            mergeEditText()
        }
    }



    private fun setupLayoutTransition() {
        baseLayout.layoutTransition = mTransitioner
        mTransitioner.addTransitionListener(object : LayoutTransition.TransitionListener {
            override fun startTransition(
                transition: LayoutTransition,
                container: ViewGroup,
                view: View,
                transitionType: Int
            ) {

            }

            override fun endTransition(
                transition: LayoutTransition,
                container: ViewGroup,
                view: View?,
                transitionType: Int
            ) {

                if (!transition.isRunning
                    && transitionType == LayoutTransition.CHANGE_DISAPPEARING
                ) {
                    mergeEditText()
                }

            }
        })
        mTransitioner.setDuration(300)
    }

    private fun mergeEditText() {
        val preView = baseLayout.getChildAt(disappearingImageIndex - 1)
        val nextView = baseLayout.getChildAt(disappearingImageIndex)

        if (preView is EditText
            && nextView is EditText
        ) {
            val preStr = preView.text.toString()
            val nextStr = nextView.text.toString()
            val strBuilder = StringBuilder(preStr)
            if (nextStr.length > 0) {
                strBuilder.append(nextStr)
            }
            baseLayout.layoutTransition = null
            baseLayout.removeView(nextView)
            preView.apply {
                setText(strBuilder.toString())
                requestFocus()
                setSelection(strBuilder.toString().length, strBuilder.toString().length)
            }
            baseLayout.layoutTransition = mTransitioner
        }
    }


    private fun createEditText(reInitHont: String, paddingTop: Int): EditText {
        val editView = inflater.inflate(R.layout.rich_editor_edit_text, null) as EditText
        editView.apply {
            setOnKeyListener(mKeyListener)
            onFocusChangeListener = mFocusListener
            tag = viewTagIndex++
            setPadding(editNormalPadding, paddingTop, editNormalPadding, paddingTop)
            hint = reInitHont
            setTextSize(TypedValue.COMPLEX_UNIT_PX, reTextSize)
            setTextColor(reTextColor)
            setLineSpacing(reTextLineSpace.toFloat(), 1.0f)
        }
        return editView
    }

    /**
     * 生成上传数据,对外提供的数据获取接口
     * @param null
     * @return
     */
    fun buidldData(): List<EditData> {
        val dataList = ArrayList<EditData>()
        val count = baseLayout.childCount
        for (i in 0..count - 1) {
            val v = baseLayout.getChildAt(i)
            val viewData = EditData()
            if (v is EditText) {
                viewData.inputStr = v.text.toString()
            } else if (v is RelativeLayout) {
                val item = v.findViewById<DataImageView>(R.id.rich_edit_image_view)
                viewData.imagePath = item.absolutePath
            }
            dataList.add(viewData)
        }

        return dataList
    }

    /**
     * 清空布局
     */
    fun clearAllLayout(){
        baseLayout.removeAllViews()
    }

    private fun initImageLoader() {
        Thread(object : Runnable {
            override fun run() {
                RichEditorImageLoader.getSingleton()
                    ?.setImageLoader((object : IRichEditorImageLoader {
                        override fun loadImage(
                            imageView: ImageView,
                            imagePath: String,
                            imageHeight: Int
                        ) {
                            LogUtil.e("******imagePath*****", imagePath)
                            if (imagePath.startsWith("http://")
                                || imagePath.startsWith("https://")
                            ) {
                                Glide.with(context)
                                    .asBitmap()
                                    .load(imagePath)
                                    .dontAnimate()
                                    .into(object : CustomTarget<Bitmap>() {
                                        override fun onLoadCleared(placeholder: Drawable?) {

                                        }

                                        override fun onResourceReady(
                                            resource: Bitmap,
                                            transition: Transition<in Bitmap>?
                                        ) {
                                            if (imageHeight > 0) {
                                                val param = RelativeLayout.LayoutParams(
                                                    LayoutParams.MATCH_PARENT, imageHeight
                                                )
                                                param.bottomMargin = imageBottomMargin
                                                imageView.layoutParams = param
                                                Glide.with(context)
                                                    .asBitmap()
                                                    .load(imagePath)
                                                    .centerCrop()
                                                    .placeholder(R.drawable.ic_rich_editor_image_view_load_fail)
                                                    .error(R.drawable.ic_rich_editor_image_view_load_fail)
                                                    .into(imageView)
                                            } else {
                                                Glide.with(context)
                                                    .asBitmap()
                                                    .load(imagePath)
                                                    .centerCrop()
                                                    .placeholder(R.drawable.ic_rich_editor_image_view_load_fail)
                                                    .error(R.drawable.ic_rich_editor_image_view_load_fail)
                                                    .into(TransformImageScale(imageView))
                                            }
                                        }

                                    })
                            } else {
                                if (imageHeight > 0) {
                                    val params = RelativeLayout.LayoutParams(
                                        ViewGroup.LayoutParams.MATCH_PARENT,
                                        imageHeight
                                    )
                                    params.bottomMargin = imageBottomMargin
                                    imageView.layoutParams = params

                                    Glide.with(context)
                                        .asBitmap()
                                        .load(imagePath)
                                        .centerCrop()
                                        .placeholder(R.drawable.ic_rich_editor_image_view_load_fail)
                                        .error(R.drawable.ic_rich_editor_image_view_load_fail)
                                        .into(imageView)
                                } else {
                                    Glide.with(context)
                                        .asBitmap()
                                        .load(imagePath)
                                        .centerCrop()
                                        .placeholder(R.drawable.ic_rich_editor_image_view_load_fail)
                                        .error(R.drawable.ic_rich_editor_image_view_load_fail)
                                        .into(TransformImageScale(imageView))
                                }

                            }
                        }
                    }))
            }
        }).start()
    }

    class EditData {
        var inputStr: String? = null
        var imagePath: String? = null
        var bitmap: Bitmap? = null
    }

}