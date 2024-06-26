package com.escola.compose.character.presentation.character.component

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.escola.compose.character.domain.model.EpisodeModel

@Composable
fun EpisodeComponent(
    episode: EpisodeModel,
    modifier: Modifier = Modifier.size(width = 160.dp, height = 190.dp),
    elevation: Dp = 6.dp,
) {

    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = elevation
        ),
        modifier = modifier
    ) {

        Column(modifier = Modifier.fillMaxSize().padding(5.dp),
            verticalArrangement =Arrangement.SpaceAround) {
            Text(
                text = "Episod: ${episode.name}",
                maxLines = 2,
                minLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Numer odcinka: ${episode.episodeNumber}",
                maxLines = 2,
                overflow = TextOverflow.Ellipsis
            )

            Text(
                text = "Data emisji: ${episode.airDate}",
                maxLines = 3,
                overflow = TextOverflow.Ellipsis
            )
        }



    }
}