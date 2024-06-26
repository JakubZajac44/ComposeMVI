package com.escola.compose.character.presentation.character_list.component

import androidx.compose.animation.AnimatedVisibilityScope
import androidx.compose.animation.ExperimentalSharedTransitionApi
import androidx.compose.animation.SharedTransitionScope
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowForward
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.AsyncImage
import com.escola.compose.character.domain.model.CharacterModel
import com.escola.compose.resource.ui.component.FlipCardComponent
import com.escola.compose.resource.ui.component.RotationDirection
import kotlin.random.Random


@OptIn(ExperimentalSharedTransitionApi::class)
@Composable
fun SharedTransitionScope.CharacterItemFront(
    modifier: Modifier,
    backgroundColor: Color = Color.White,
    characterItemClick: (Int, String, String) -> Unit,
    characterModel: CharacterModel,
    animatedVisibilityScope: AnimatedVisibilityScope,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxSize()
        .background(color = backgroundColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(
            modifier = Modifier
                .fillMaxSize(),

            verticalAlignment = Alignment.CenterVertically
        ) {
            AsyncImage(
                modifier = Modifier
                    .width(115.dp)
                    .height(120.dp)
                    .sharedElement(
                        state = rememberSharedContentState(key = "image-${characterModel.id}"),
                        animatedVisibilityScope = animatedVisibilityScope
                    ),
                model = characterModel.image,
                contentDescription = characterModel.name,
                contentScale = ContentScale.Crop
            )
            Spacer(modifier = Modifier.width(10.dp))

            Row(
                modifier = Modifier
                    .fillMaxSize(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.End

            ) {
                Text(
                    text = characterModel.name,
                    fontSize = 24.sp,
                    maxLines = 2,
                    modifier = Modifier

                        .weight(1f)
                        .sharedBounds(
                            sharedContentState = rememberSharedContentState(key = "text-${characterModel.id}"),
                            animatedVisibilityScope = animatedVisibilityScope
                        )
                )
                Spacer(modifier = Modifier.width(10.dp))
                IconButton(
                    onClick = {
                        characterItemClick.invoke(
                            characterModel.id,
                            characterModel.name,
                            characterModel.image
                        )
                    }) {
                    Icon(
                        modifier = Modifier
                            .size(50.dp),
                        imageVector = Icons.AutoMirrored.Filled.ArrowForward,
                        contentDescription = "back"
                    )
                }
            }


        }
    }
}

