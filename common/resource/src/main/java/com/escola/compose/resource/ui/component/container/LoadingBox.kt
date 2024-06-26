package com.escola.compose.resource.ui.component.container

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.BoxScope
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.blur
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.escola.compose.resource.ui.component.dot_progress_bar.ProgressDotIndicator

@Composable
fun LoadingBox(
    isLoading: Boolean,
    blur: Dp = 10.dp,
    progressColor: Color = MaterialTheme.colorScheme.primary,
    content: @Composable BoxScope.() -> Unit
) {
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Transparent)
    ) {

        Box(
            modifier = Modifier
                .fillMaxSize()
                .blur(if (isLoading) blur else 0.dp)
        ) {
            content.invoke(this)
        }

        if (isLoading) {
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .background(Color.Transparent),
                contentAlignment = Alignment.Center,
            ) {
                ProgressDotIndicator(
                    modifier = Modifier.size(80.dp),
                    progressColor = progressColor,
                )
            }
        }
    }

}

@Preview
@Composable
fun LoadingBoxProgressOffPreview() {
    LoadingBox(
        isLoading = false
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
            contentAlignment = Alignment.Center) {
            Text("Preview")
        }

    }
}

@Preview
@Composable
fun LoadingBoxProgressOnPreview() {
    LoadingBox(
        isLoading = true
    ) {
        Box(modifier = Modifier
            .fillMaxSize()
            .background(Color.White),
            contentAlignment = Alignment.Center) {
            Text("Preview")
        }

    }
}