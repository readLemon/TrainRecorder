package com.example.train.widget.interfaces

import com.example.train.widget.SlideLayout

/**
 * Created by chenyang
 * on 20-3-13
 */
interface onSlideChangeListener {

    fun onMenuOpen(slide: SlideLayout)
    fun onMenuClose(slide: SlideLayout)
    fun onMenuClick(slide: SlideLayout)
}