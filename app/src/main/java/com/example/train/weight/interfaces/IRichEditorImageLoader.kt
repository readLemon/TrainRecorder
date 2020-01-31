package com.example.train.weight.interfaces

import android.widget.ImageView

/**
 * Created by chenyang
 * on 20-1-31
 */
interface IRichEditorImageLoader {
    fun loadImage(imageView: ImageView, imagePath: String,imageHeight: Int)
}