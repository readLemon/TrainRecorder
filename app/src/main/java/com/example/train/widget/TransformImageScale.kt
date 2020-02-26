package com.example.train.widget

import android.graphics.Bitmap
import android.widget.ImageView
import android.widget.RelativeLayout
import com.bumptech.glide.request.target.ImageViewTarget

/**
 * Created by chenyang
 * on 20-1-31
 */
class TransformImageScale(val targetImageView: ImageView): ImageViewTarget<Bitmap>(targetImageView) {
    override fun setResource(resource: Bitmap?) {

        view.setImageBitmap(resource)

        if (resource != null) {
            val width = resource.width
            val height = resource.height

            val imageWidth = targetImageView.width

            val sy = imageWidth * 0.1 / (width * 0.1)//计算缩放比例
            val imageHeight = height * sy

            val param = targetImageView.layoutParams as RelativeLayout.LayoutParams
            param.bottomMargin = RichEditor.imageBottomMargin
            param.height = imageHeight.toInt()

            targetImageView.layoutParams = param

        }
    }
}