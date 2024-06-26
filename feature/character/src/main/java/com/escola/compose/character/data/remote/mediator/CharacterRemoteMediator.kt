package com.escola.compose.character.data.remote.mediator

import androidx.paging.ExperimentalPagingApi
import androidx.paging.LoadType
import androidx.paging.PagingState
import androidx.paging.RemoteMediator
import androidx.room.withTransaction
import com.escola.compose.character.data.local.data_source.CharacterLocalDataSource
import com.escola.compose.character.data.local.db.CharacterDateBase
import com.escola.compose.character.data.local.model.CharacterEntity
import com.escola.compose.character.data.local.model.CharacterRemoteKeyEntity
import com.escola.compose.character.data.mapper.toCharacterEntity
import com.escola.compose.character.data.remote.data_source.CharacterRemoteDataSource
import com.escola.compose.resource.ApiResult

@OptIn(ExperimentalPagingApi::class)
class CharacterRemoteMediator(
    private val characterDb: CharacterDateBase,
    private val characterRemoteDataSource: CharacterRemoteDataSource,
    private val characterLocalDataSource: CharacterLocalDataSource
) : RemoteMediator<Int, CharacterEntity>() {


    override suspend fun load(
        loadType: LoadType,
        state: PagingState<Int, CharacterEntity>,
    ): MediatorResult {

            val currentPage = when(loadType){
                LoadType.REFRESH -> {
                    val remoteKeys = getRemoteKeyClosestToCurrentPosition(state)
                    remoteKeys?.nextPage?.minus(1) ?: 1
                }
                LoadType.PREPEND -> {
                    val remoteKeys = getRemoteKeyForFirstItem(state)
                    val prevPage = remoteKeys?.prevPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    prevPage
                }
                LoadType.APPEND -> {
                    val remoteKeys = getRemoteKeyForLastItem(state)
                    val nextPage = remoteKeys?.nextPage
                        ?: return MediatorResult.Success(
                            endOfPaginationReached = remoteKeys != null
                        )
                    nextPage
                }
            }


            val responseData = when(val response = characterRemoteDataSource.getCharacterListByPage(currentPage)){
                is ApiResult.Success ->       response.data.results
                is ApiResult.Error ->  return MediatorResult.Error(response.exception)

            }


            val endOfPaginationReached = responseData.isEmpty()

            val prevPage = if (currentPage == 1) null else currentPage - 1
            val nextPage = if (endOfPaginationReached) null else currentPage + 1

            characterDb.withTransaction {
                if (loadType == LoadType.REFRESH) {
                    characterLocalDataSource.clearAllCharacterListPaged()
                    characterLocalDataSource.deleteAllRemoteKeys()
                }

                val keys = responseData.map { character ->
                    CharacterRemoteKeyEntity(
                        id = character.id,
                        prevPage = prevPage,
                        nextPage = nextPage
                    )
                }

                characterLocalDataSource.addAllRemoteKeys(remoteKeys = keys)
                val bookEntities = responseData.map {it.toCharacterEntity()}
                characterLocalDataSource.insertAllCharacterListPaged(bookEntities)
            }

            return MediatorResult.Success(
                endOfPaginationReached = responseData.isEmpty()
            )
    }


    private suspend fun getRemoteKeyForFirstItem(
        state: PagingState<Int, CharacterEntity>
    ): CharacterRemoteKeyEntity? {
        return state.pages.firstOrNull { it.data.isNotEmpty() }?.data?.firstOrNull()
            ?.let { character ->
                characterLocalDataSource.getRemoteKeys(id = character.id.toString())
            }
    }

    private suspend fun getRemoteKeyForLastItem(
        state: PagingState<Int, CharacterEntity>
    ): CharacterRemoteKeyEntity? {
        return state.pages.lastOrNull { it.data.isNotEmpty() }?.data?.lastOrNull()
            ?.let { character ->
                characterLocalDataSource.getRemoteKeys(id = character.id.toString())
            }
    }

    private suspend fun getRemoteKeyClosestToCurrentPosition(
        state: PagingState<Int, CharacterEntity>
    ): CharacterRemoteKeyEntity? {
        return state.anchorPosition?.let { position ->
            state.closestItemToPosition(position)?.id?.let { id ->
                characterLocalDataSource.getRemoteKeys(id = id.toString())
            }
        }
    }

}


