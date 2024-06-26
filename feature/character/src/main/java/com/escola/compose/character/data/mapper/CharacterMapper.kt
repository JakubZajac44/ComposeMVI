package com.escola.compose.character.data.mapper

import com.escola.compose.character.data.local.model.CharacterEntity
import com.escola.compose.character.data.remote.model.CharacterDto
import com.escola.compose.character.domain.model.CharacterDetailsModel
import com.escola.compose.character.domain.model.CharacterModel
import com.escola.compose.character.domain.model.Gender
import com.escola.compose.character.domain.model.Species

fun CharacterDto.toCharacter(): CharacterModel {
    return CharacterModel(
        id = id,
        name = name,
        isAlive = status.toStatus(),
        species = species.toSpecies(),
        gender = gender.toGender(),
        image = image
    )
}

fun CharacterDto.toCharacterDetails(): CharacterDetailsModel {
    return CharacterDetailsModel(
        id = id,
        name = name,
        episodeList = episode
    )
}

fun CharacterEntity.toCharacter() : CharacterModel{
    return CharacterModel(
        id = id,
        name = name,
        isAlive = isAlive,
        species = species,
        gender = gender,
        image = image
    )
}

fun CharacterModel.toCharacterEntity() : CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        isAlive = isAlive,
        species = species,
        gender = gender,
        image = image
    )
}

fun CharacterDto.toCharacterEntity() : CharacterEntity {
    return CharacterEntity(
        id = id,
        name = name,
        isAlive = status.toStatus(),
        species = species.toSpecies(),
        gender = gender.toGender(),
        image = image
    )
}




private fun String.toGender(): Gender {
    return when (this) {
        "Male" -> Gender.MALE
        "Female" -> Gender.FEMALE
        else -> Gender.OTHER
    }
}

private fun String.toSpecies(): Species {
    return when (this) {
        "Human" -> Species.HUMAN
        "Alien" -> Species.ALIEN
        else -> Species.OTHER
    }
}

private fun String.toStatus(): Boolean {
    return this == "Alive"
}