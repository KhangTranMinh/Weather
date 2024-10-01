package com.ktm.weather.presentation.ui.base

import androidx.lifecycle.ViewModel
import com.ktm.weather.presentation.util.Logger

open class BaseViewModel : ViewModel() {

    protected val logger = Logger.init(this::class.java.simpleName)
}