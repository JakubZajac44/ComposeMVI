package com.escola.compose.character.presentation.character

import android.util.Log
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.viewModelScope
import com.escola.compose.character.CharacterIdArgs
import com.escola.compose.character.domain.model.EpisodeModel
import com.escola.compose.character.domain.use_case.GetCharacterDetailsUseCase
import com.escola.compose.character.domain.use_case.GetCharacterEpisodeUseCase
import com.escola.compose.resource.Resource
import com.escola.compose.resource.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Deferred
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.merge
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import java.net.URLDecoder
import javax.inject.Inject


@HiltViewModel
class CharacterViewModel @Inject constructor(
    savedStateHandle: SavedStateHandle,
    private val getCharacterDetailsUseCase: GetCharacterDetailsUseCase,
    private val getCharacterEpisodeUseCase: GetCharacterEpisodeUseCase,
) : BaseViewModel<CharacterState, CharacterEvent, CharacterEffect>(
    initialState = CharacterState()
) {

    private val args = CharacterIdArgs(savedStateHandle)


    init {
        getCharacterDetails()
    }

    fun setInitialData(id: String, name: String, imageUrl: String) {
        _state.update {
            it.copy(
                characterId = id,
                name = name,
                imageUrl = URLDecoder.decode(imageUrl, "UTF-8")
            )
        }
    }

    override fun onEvent(event: CharacterEvent) {
        when (event) {
            CharacterEvent.RefreshData -> getCharacterDetails(true)
        }
    }


    private fun getCharacterDetails(isRefreshing: Boolean = false) {
        if (isRefreshing) _state.update {
            it.copy(
                isRefreshing = true
            )
        }
        viewModelScope.launch {
            getCharacterDetailsUseCase(args.characterId)
                .collect { result ->
                    when (result) {
                        is Resource.Error -> {
                            _state.update {
                                it.copy(
                                    characterDetails = null,
                                    isLoadingCharacter = false,
                                    isLoadingEpisode = false,
                                    isRefreshing = false,
                                    error = result.exception.message.toString()
                                )
                            }
                        }

                        is Resource.Success -> {
                            _state.update {
                                it.copy(
                                    characterDetails = result.data,
                                    isLoadingCharacter = false,
                                    isRefreshing = false,
                                    error = null
                                )
                            }
                            getCharacterEpisodes(result.data.episodeList)
                        }

                        is Resource.Loading -> {
                            _state.update {
                                it.copy(
                                    isLoadingCharacter = result.isLoading,
                                    isLoadingEpisode = true,
                                    error = null
                                )
                            }
                        }

                        else -> {}
                    }
                }

        }
    }

    private fun getCharacterEpisodes(episodeIdList: List<String>) {
        Log.e("TAG", "TAG: ${episodeIdList.size}")
        viewModelScope.launch {


            val mergedRequest = mutableListOf<Deferred<Flow<Resource<EpisodeModel>>>>()

            episodeIdList.forEach { id ->
                val idd = id.split("/").last()
                mergedRequest.add(async {
                    val response = getCharacterEpisodeUseCase.invoke(idd)
                    response
                })
            }
            val listOfEpisode: MutableList<EpisodeModel> = mutableListOf()
            mergedRequest.awaitAll().merge().collect { result ->
                when (result) {
                    is Resource.Error -> {}
                    is Resource.Loading -> {}
                    is Resource.Success -> listOfEpisode.add(result.data)
                }
            }

            _state.update {
                it.copy(
                    characterEpisodeDetails = listOfEpisode,
                    isLoadingEpisode = false,
                    error = null
                )
            }


        }
    }

}