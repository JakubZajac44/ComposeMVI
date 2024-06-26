package com.escola.compose.resource.ui.component

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.animateFloat
import androidx.compose.animation.core.infiniteRepeatable
import androidx.compose.animation.core.rememberInfiniteTransition
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.composed
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.onGloballyPositioned

import androidx.compose.ui.unit.IntSize
import androidx.compose.ui.unit.dp

@Composable
fun ShimmerText(
    isLoading: Boolean,
    contentAfterLoading: @Composable () -> Unit,
    modifier: Modifier = Modifier,
    colorList: List<Color> = listOf(),
) {
    val colorListDark = listOf(
        Color(0x41EBEBF4),
        Color(0x6AEBEBF4),
        Color(0x41EBEBF4),
    )
    val colorListLight = listOf(
        Color(0xFFEBEBF4),
        Color(0xFFF4F4F4),
        Color(0xFFEBEBF4),
    )


    Box() {
        AnimatedVisibility(
            visible = isLoading,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            Box(
                modifier = modifier
                    .shimmerEffect(
                        if (colorList.isNotEmpty()) colorList
                        else if (isSystemInDarkTheme()) colorListDark
                        else colorListLight
                    )
            )
        }
        AnimatedVisibility(
            visible = !isLoading,
            enter = fadeIn(animationSpec = tween(500)),
            exit = fadeOut(animationSpec = tween(300))
        ) {
            contentAfterLoading()
        }
    }
}


fun Modifier.shimmerEffect(
    colorList: List<Color>,
): Modifier = composed {
    var size by remember {
        mutableStateOf(IntSize.Zero)
    }
    val transition = rememberInfiniteTransition(label = "")
    val startOffSetX by transition.animateFloat(
        initialValue = -1.5f * size.width.toFloat(),
        targetValue = 1.5f * size.width.toFloat(),
        animationSpec = infiniteRepeatable(
            animation = tween(2 * size.width)
        ), label = ""
    )

    background(
        brush = Brush.linearGradient(
            colors = colorList,
            start = Offset(startOffSetX, 0f),
            end = Offset(startOffSetX + size.width.toFloat(), size.height.toFloat())
        )
    )
        .onGloballyPositioned {
            size = it.size
        }
}