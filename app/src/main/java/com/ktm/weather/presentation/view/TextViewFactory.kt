package com.ktm.weather.presentation.view

import android.content.Context
import android.graphics.Typeface
import android.view.Gravity
import android.view.View
import android.widget.TextView
import android.widget.ViewSwitcher
import androidx.annotation.StyleRes

class TextViewFactory(
    private val context: Context,
    @StyleRes private val styleId: Int = 0,
    private val center: Boolean = false,
    private val typeface: Typeface? = null
) : ViewSwitcher.ViewFactory {

    override fun makeView(): View {
        val textView = TextView(context)
        if (center) textView.gravity = Gravity.CENTER
        textView.setTextAppearance(styleId)
        textView.typeface = typeface
        return textView
    }
}