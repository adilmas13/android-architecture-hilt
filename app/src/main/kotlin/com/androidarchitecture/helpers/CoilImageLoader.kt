package com.androidarchitecture.helpers

import android.widget.ImageView
import coil.api.load
import coil.transform.BlurTransformation
import coil.transform.CircleCropTransformation
import coil.transform.GrayscaleTransformation
import coil.transform.RoundedCornersTransformation
import com.androidarchitecture.utilities.ImageLoader

class CoilImageLoader : ImageLoader {

    override fun loadImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(1000)
        }
    }

    override fun loadCircularImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(1000)
            transformations(CircleCropTransformation())
        }
    }

    override fun loadBlurImage(
        view: ImageView,
        url: String,
        radius: Float,
        sampling: Float
    ) {
        view.load(url) {
            crossfade(1000)
            transformations(BlurTransformation(view.context, radius, sampling))
        }
    }

    override fun loadRoundedCornersImage(view: ImageView, url: String, radius: Float) {
        view.load(url) {
            crossfade(1000)
            transformations(RoundedCornersTransformation(radius))
        }
    }

    override fun loadGreyScaleImage(view: ImageView, url: String) {
        view.load(url) {
            crossfade(1000)
            transformations(GrayscaleTransformation())
        }
    }
}
