package com.androidarchitecture.utilities

import android.app.Activity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.annotation.LayoutRes
import androidx.annotation.StringRes
import androidx.fragment.app.Fragment
import com.google.android.material.bottomsheet.BottomSheetBehavior

fun View.makeVisible() {
    this.visibility = View.VISIBLE
}

fun View.makeGone() {
    this.visibility = View.GONE
}

fun View.isVisible(): Boolean {
    return this.visibility == View.VISIBLE
}

fun View.isGone(): Boolean {
    return this.visibility == View.GONE
}

fun BottomSheetBehavior<*>.expand() {
    this.state = BottomSheetBehavior.STATE_EXPANDED
}

fun BottomSheetBehavior<*>.collapse() {
    this.state = BottomSheetBehavior.STATE_COLLAPSED
}

fun BottomSheetBehavior<*>?.isCollapsed(): Boolean {
    return (this?.state == BottomSheetBehavior.STATE_COLLAPSED)
}

fun BottomSheetBehavior<*>?.isExpanded(): Boolean {
    return (this?.state == BottomSheetBehavior.STATE_EXPANDED)
}

fun ViewGroup.inflate(@LayoutRes layoutId: Int, attachToRoot: Boolean = false): View {
    return LayoutInflater.from(this.context).inflate(layoutId, this, attachToRoot)
}

fun Activity.showToast(message: String): Toast {
    val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
    toast.show()
    return toast
}

fun Fragment.showToast(message: String): Toast {
    val toast = Toast.makeText(this.context, message, Toast.LENGTH_LONG)
    toast.show()
    return toast
}

fun View.setToolTip(@StringRes toolTip: Int) {
    this.setOnLongClickListener {
        Toast.makeText(this.context, toolTip, Toast.LENGTH_SHORT).show(); true
    }
}
