package com.androidarchitecture.utilities

import android.widget.ImageView
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation

object Imagify {
    fun loadImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(1000)
        }
    }

    fun loadCircularImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(1000)
            transformations(CircleCropTransformation())
        }
    }

    fun loadBlurImage(view: ImageView, url: String, radius: Float = 10f, sampling: Float = 1f) {
        view.load(url) {
            crossfade(1000)
            transformations(BlurTransformation(view.context, radius, sampling))
        }
    }

    fun loadRoundedCornersImage(view: ImageView, url: String, radius: Float = 0f) {
        view.load(url) {
            crossfade(1000)
            transformations(RoundedCornersTransformation(radius))
        }
    }

    fun loadGreyScaleImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(1000)
            transformations(GrayscaleTransformation())
        }
    }
}
