package com.escola.compose.resource.ui.component

import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Card
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer

@Composable
fun FlipCardComponent(
    modifier: Modifier = Modifier,
    rotationDirection: RotationDirection,
    animationTime: Int = 1000,
    back: @Composable () -> Unit = {},
    front: @Composable () -> Unit = {},
) {

    var cardFace by rememberSaveable {
        mutableStateOf(CardFace.Front)
    }

    val rotation = animateFloatAsState(
        targetValue = cardFace.angle,
        animationSpec = tween(
            durationMillis = animationTime,
            easing = LinearOutSlowInEasing,
        ), label = ""
    )
    Card(
        onClick = { cardFace = cardFace.next },
        modifier = modifier
            .graphicsLayer {
                if (rotationDirection.rotationList.contains(RotationAxis.AxisX)) {
                    rotationX = rotation.value
                }
                if (rotationDirection.rotationList.contains(RotationAxis.AxisY)) {
                    rotationY = rotation.value
                }
                if (rotationDirection.rotationList.contains(RotationAxis.AxisZ)) {
                    rotationZ = rotation.value
                }
                cameraDistance = 12f * density
            },
    ) {
        Box(
            modifier = Modifier.fillMaxSize()
        ) {
            if (rotation.value <= 90f) {
                Box(
                    Modifier.fillMaxSize()
                ) {
                    front()
                }
            } else {
                Box(
                    Modifier
                        .fillMaxSize()
                        .graphicsLayer {
                            if (rotationDirection.rotationList.contains(RotationAxis.AxisX)) {
                                rotationX = 180f
                            }
                            if (rotationDirection.rotationList.contains(RotationAxis.AxisY)) {
                                rotationY = 180f
                            }
                            if (rotationDirection.rotationList.contains(RotationAxis.AxisZ)) {
                                rotationZ = 180f
                            }
                        },
                ) {
                    back()
                }
            }
        }
    }
}


enum class CardFace(val angle: Float) {
    Front(0f) {
        override val next: CardFace
            get() = Back
    },
    Back(180f) {
        override val next: CardFace
            get() = Front
    };

    abstract val next: CardFace
}

enum class RotationAxis {
    AxisX,
    AxisY,
    AxisZ,
}

enum class RotationDirection(val rotationList: List<RotationAxis>) {
    RotationX(listOf(RotationAxis.AxisX)),
    RotationY(listOf(RotationAxis.AxisY)),
    RotationZ(listOf(RotationAxis.AxisZ)),
    RotationXY(listOf(RotationAxis.AxisX, RotationAxis.AxisY)),
    RotationXZ(listOf(RotationAxis.AxisX, RotationAxis.AxisZ)),
    RotationYZ(listOf(RotationAxis.AxisY, RotationAxis.AxisZ)),
    RotationXYZ(listOf(RotationAxis.AxisX, RotationAxis.AxisY, RotationAxis.AxisZ)),
}
