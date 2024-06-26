package com.escola.compose.character.data.mapper

import com.escola.compose.character.data.remote.model.EpisodeDto
import com.escola.compose.character.domain.model.EpisodeModel

fun EpisodeDto.toEpisodeModel(): EpisodeModel{
    return EpisodeModel(
        id = this.id,
        name = this.name,
        episodeNumber = this.episode,
        airDate = this.air_date
    )
}