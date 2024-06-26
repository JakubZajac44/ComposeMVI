package com.escola.compose.character.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.escola.compose.character.domain.model.Gender
import com.escola.compose.character.domain.model.Species

@Entity
data class CharacterEntity (
    @PrimaryKey
    val id: Int,
    val name: String,
    val isAlive: Boolean,
    val species: Species,
    val gender: Gender,
    val image: String
)
