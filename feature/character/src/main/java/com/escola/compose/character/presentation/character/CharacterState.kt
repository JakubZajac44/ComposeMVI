package com.escola.compose.character.presentation.character

import com.escola.compose.character.domain.model.CharacterDetailsModel
import com.escola.compose.character.domain.model.EpisodeModel
import com.escola.compose.resource.viewmodel.Reducer

data class CharacterState(
    val characterDetails: CharacterDetailsModel? = null,
    val characterEpisodeDetails: List<EpisodeModel>? = null,
    val isLoadingCharacter: Boolean = false,
    val isLoadingEpisode: Boolean = false,
    val isRefreshing: Boolean = false,
    val error: String? = null,
    val characterId: String = "",
    val name: String = "",
    val imageUrl: String = ""
) : Reducer.ViewState