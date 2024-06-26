package com.escola.compose.character.data.local.data_source

import androidx.paging.PagingSource
import com.escola.compose.character.data.local.dao.CharacterDao
import com.escola.compose.character.data.local.dao.CharacterKeyDao
import com.escola.compose.character.data.local.model.CharacterEntity
import com.escola.compose.character.data.local.model.CharacterRemoteKeyEntity
import javax.inject.Inject

class CharacterLocalDataSource @Inject constructor(
    private val characterDao: CharacterDao,
    private val characterKeyDao: CharacterKeyDao,
) {

    fun getCharacterListPaged(): PagingSource<Int, CharacterEntity> =
        characterDao.pagingSource()

    suspend fun clearAllCharacterListPaged() {
        characterDao.clearAll()
    }

    suspend fun insertAllCharacterListPaged(list: List<CharacterEntity>) {
        characterDao.upsertAll(list)
    }

    suspend fun getRemoteKeys(id: String) = characterKeyDao.getRemoteKeys(id)


    suspend fun addAllRemoteKeys(remoteKeys: List<CharacterRemoteKeyEntity>) {
        characterKeyDao.addAllRemoteKeys(remoteKeys)
    }

    suspend fun deleteAllRemoteKeys() {
        characterKeyDao.deleteAllRemoteKeys()
    }
}