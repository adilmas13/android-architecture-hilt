package com.androidarchitecture.utilities

import android.widget.ImageView

interface ImageLoader {

    fun loadImage(view: ImageView, url: String)

    fun loadCircularImage(view: ImageView, url: String)

    fun loadBlurImage(view: ImageView, url: String, radius: Float, sampling: Float)

    fun loadRoundedCornersImage(view: ImageView, url: String, radius: Float = 0f)

    fun loadGreyScaleImage(view: ImageView, url: String)
}
