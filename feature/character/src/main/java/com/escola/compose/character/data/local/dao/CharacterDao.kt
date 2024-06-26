package com.escola.compose.character.data.local.dao

import androidx.paging.PagingSource
import androidx.room.Dao
import androidx.room.Query
import androidx.room.Upsert
import com.escola.compose.character.data.local.model.CharacterEntity

@Dao
interface CharacterDao {

    @Upsert
    suspend fun upsertAll(character: List<CharacterEntity>)

    @Query("Select * from CharacterEntity")
    fun pagingSource(): PagingSource<Int, CharacterEntity>

    @Query("DELETE FROM CharacterEntity")
    suspend fun clearAll()
}