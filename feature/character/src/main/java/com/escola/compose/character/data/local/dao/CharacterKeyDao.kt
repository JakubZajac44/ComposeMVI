package com.escola.compose.character.data.local.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.escola.compose.character.data.local.model.CharacterRemoteKeyEntity

@Dao
interface CharacterKeyDao {

    @Query("SELECT * FROM character_remote_key_model WHERE id =:id")
    suspend fun getRemoteKeys(id: String): CharacterRemoteKeyEntity

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun addAllRemoteKeys(remoteKeys: List<CharacterRemoteKeyEntity>)

    @Query("DELETE FROM character_remote_key_model")
    suspend fun deleteAllRemoteKeys()
}