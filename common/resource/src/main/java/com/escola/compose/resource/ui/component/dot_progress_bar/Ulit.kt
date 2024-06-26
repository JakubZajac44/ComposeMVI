package com.escola.compose.resource.ui.component.dot_progress_bar

import kotlin.math.PI
import kotlin.math.absoluteValue

val Float.alphaFromRadians: Float
    get() {
        val normalized = (this / (2f * PI)).toFloat()
        return .5f + (normalized - .5f).absoluteValue
    }