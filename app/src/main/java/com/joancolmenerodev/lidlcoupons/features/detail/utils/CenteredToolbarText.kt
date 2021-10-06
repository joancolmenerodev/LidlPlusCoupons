package com.joancolmenerodev.lidlcoupons.features.detail.utils

import android.content.Context
import android.graphics.Typeface
import android.text.TextUtils
import android.util.AttributeSet
import android.view.Gravity
import android.widget.TextView
import androidx.annotation.StringRes
import androidx.appcompat.widget.Toolbar
import androidx.core.widget.TextViewCompat
import com.joancolmenerodev.lidlcoupons.R

/**
 * Custom class to show a centered Toolbar
 */

class CenteredToolbarText @JvmOverloads constructor(
    context: Context,
    attrs: AttributeSet? = null,
    defStyleAttr: Int = 0
) : Toolbar(context, attrs, defStyleAttr) {

    private lateinit var centeredTitleTextView: TextView

    override fun setTitle(@StringRes resId: Int) {
        val text = resources.getString(resId)
        title = text
    }


    override fun setTitle(title: CharSequence?) {
        getCenteredTitleTextView().text = title
    }

    override fun getTitle(): CharSequence? {
        return getCenteredTitleTextView().text.toString()
    }

    fun setTypeface(font: Typeface?) {
        getCenteredTitleTextView().typeface = font
    }

    private fun getCenteredTitleTextView(): TextView {
        centeredTitleTextView = TextView(context)
        centeredTitleTextView.setSingleLine()
        centeredTitleTextView.ellipsize = TextUtils.TruncateAt.END
        centeredTitleTextView.gravity = Gravity.CENTER
        TextViewCompat.setTextAppearance(
            centeredTitleTextView,
            R.style.TextAppearance_AppCompat_Widget_ActionBar_Title
        )
        val lp = LayoutParams(
            LayoutParams.WRAP_CONTENT,
            LayoutParams.WRAP_CONTENT
        )
        lp.gravity = Gravity.CENTER
        centeredTitleTextView.layoutParams = lp

        addView(centeredTitleTextView)

        return centeredTitleTextView
    }
}

