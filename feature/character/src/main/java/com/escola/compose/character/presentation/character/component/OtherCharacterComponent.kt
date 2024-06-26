package com.escola.compose.character.presentation.character.component

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import coil.compose.AsyncImage

@Composable
fun OtherCharacterComponent(
    imageUrl: String,
    modifier: Modifier = Modifier.size(300.dp)
) {
    Box(modifier = modifier){
        AsyncImage(
            modifier = Modifier
                .fillMaxSize(),
            model =imageUrl,
            contentDescription = "Text",
        )
    }
}