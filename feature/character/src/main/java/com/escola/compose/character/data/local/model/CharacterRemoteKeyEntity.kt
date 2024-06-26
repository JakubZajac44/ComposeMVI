package com.escola.compose.character.data.local.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "character_remote_key_model")
data class CharacterRemoteKeyEntity (
    @PrimaryKey(autoGenerate = false)
    val id: Int,
    val prevPage: Int?,
    val nextPage: Int?
)