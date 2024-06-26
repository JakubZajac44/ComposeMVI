package com.escola.compose.character.presentation.character_list.component

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.layout.wrapContentWidth
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Checkbox
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.escola.compose.character.domain.model.CharacterModel

@Composable
fun CharacterItemBack(
    modifier: Modifier = Modifier,
    characterModel: CharacterModel,
    backgroundColor: Color = Color.White,
) {
    ElevatedCard(
        modifier = modifier
            .fillMaxSize()
            .background(color = backgroundColor),
        elevation = CardDefaults.cardElevation(
            defaultElevation = 8.dp
        )
    ) {
        Row(modifier = modifier
            .padding(10.dp)) {

            Column(
                modifier = Modifier
                    .wrapContentWidth()
                    .fillMaxHeight()
                    .padding(horizontal = 10.dp),
                verticalArrangement = Arrangement.SpaceAround
            ) {
                Row(modifier = Modifier) {
                    Text(
                        "Żyje"
                    )
                    Spacer(modifier = Modifier.width(10.dp))
                    Checkbox(
                        checked = characterModel.isAlive,
                        onCheckedChange = null
                    )
                }

                Row(modifier = Modifier) {
                    Text(
                        "Płeć ${characterModel.gender}"
                    )

                }

                Row(modifier = Modifier) {
                    Text(
                        "Gatunek ${characterModel.species}"
                    )

                }
            }

            Text(text = "Lorem Ipsum is simply dummy text of the printing and typesetting industry. Lorem Ipsum has been the industry's standard dummy text ever since the 1500s, when an unknown printer took a galley of type and scrambled it to make a type specimen book. It has survived not only five centuries, but also the leap into electronic typesetting, remaining essentially unchanged. It was popularised in the 1960s with the release of Letraset sheets containing Lorem Ipsum passages, and more recently with desktop publishing software like Aldus PageMaker including versions of Lorem Ipsum.")

        }
    }
}