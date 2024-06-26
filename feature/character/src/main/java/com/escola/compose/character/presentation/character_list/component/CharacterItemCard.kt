package com.escola.compose.character.presentation.character_list.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.escola.compose.character.domain.model.CharacterModel
import com.escola.compose.resource.ui.component.FlipCardComponent
import com.escola.compose.resource.ui.component.RotationDirection

@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CharacterItemCard(
    modifier: Modifier = Modifier,
    height: Dp = 120.dp,
    characterModel: CharacterModel,
    backgroundColor: Color = Color.White,
    animatedVisibilityScope: AnimatedVisibilityScope,
    characterItemClick: (Int, String, String) -> Unit,
) {

    FlipCardComponent(
        rotationDirection = RotationDirection.RotationX,
        front = {
            CharacterItemFront(
                modifier = modifier
                    .fillMaxWidth()
                    .height(height),
                characterItemClick = { id, name, url -> characterItemClick.invoke(id, name, url) },
                characterModel = characterModel,
                animatedVisibilityScope = animatedVisibilityScope,
                backgroundColor = backgroundColor
            )
        },
        back = {

            CharacterItemBack(
                modifier = modifier
                    .fillMaxWidth()
                    .height(height),
                characterModel = characterModel,
                backgroundColor= backgroundColor,
            )
        }
    )
}