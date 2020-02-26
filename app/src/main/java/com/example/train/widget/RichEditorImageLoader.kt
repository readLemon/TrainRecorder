package com.example.train.widget

import android.widget.ImageView
import com.example.train.widget.interfaces.IRichEditorImageLoader

/**
 * Created by chenyang
 * on 20-1-31
 */
//object RichEditorImageLoader {
//
//    private var instance: RichEditorImageLoader ?= null
//    val imageLoader: IRichEditorImageLoader ?= null
//
//    fun getInstance() {
//        if (instance ==null) {
//            synchronized(RichEditorImageLoader::class){
//                  instance = this
//            }
//        }
//    }
//}
class RichEditorImageLoader private constructor() {
    private var imageLoader: IRichEditorImageLoader?= null
    companion object {
        private val instance: RichEditorImageLoader? = null

            get() {
                if (field == null) {
                    return RichEditorImageLoader()
                }
                return field
            }
        @Synchronized
        fun getSingleton(): RichEditorImageLoader? {
            return instance
        }
    }

    fun setImageLoader(imageLoader: IRichEditorImageLoader) {
        this.imageLoader = imageLoader
    }

    fun loadImage(imageView: ImageView, imagePath:String, imageHeight: Int) {
        if (imageLoader != null) {
            (imageLoader as IRichEditorImageLoader).loadImage(imageView, imagePath, imageHeight)
        }
    }

}
