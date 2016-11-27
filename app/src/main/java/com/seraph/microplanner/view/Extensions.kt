package com.seraph.microplanner.view

import android.content.Context
import android.util.TypedValue

/**
 * Created by Alex on 27.11.2016.
 */

internal fun Int.dp(context: Context): Float = TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), context.resources.displayMetrics)