package com.escola.compose.character.domain.model

data class CharacterDetailsModel (
    val id: Int,
    val name: String,
    val episodeList: List<String>
)
