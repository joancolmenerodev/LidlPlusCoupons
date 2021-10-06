package com.joancolmenerodev.lidlcoupons.base.ui

import android.content.Context
import android.util.AttributeSet
import com.google.android.material.appbar.AppBarLayout


/**
 * Create a fix app bar layout behavior to avoid scroll in the appbar
 */
class FixedAppBarLayoutBehavior(context: Context, attrs: AttributeSet) :
    AppBarLayout.Behavior(context, attrs) {
    init {
        setDragCallback(object : DragCallback() {
            override fun canDrag(appBarLayout: AppBarLayout): Boolean = false
        })
    }
}