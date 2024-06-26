package com.escola.compose.character.domain.model

data class CharacterModel(
    val id: Int,
    val name: String,
    val isAlive: Boolean,
    val species: Species,
    val gender: Gender,
    val image: String
)

enum class Gender{
    MALE, FEMALE, OTHER
}

enum class Species{
    HUMAN, ALIEN, OTHER
}


