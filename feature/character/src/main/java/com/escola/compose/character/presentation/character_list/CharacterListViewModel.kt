package com.escola.compose.character.presentation.character_list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.escola.compose.character.domain.use_case.GetCharacterListUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import androidx.paging.cachedIn
import androidx.paging.compose.collectAsLazyPagingItems
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.first
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    val getCharacterListUseCase: GetCharacterListUseCase,
) : ViewModel() {

    var characterListFlow = getCharacterListUseCase.invoke()
        .cachedIn(viewModelScope)

    private var _state = MutableStateFlow(CharacterListState())
    val state = _state.asStateFlow()

    fun onEvent(event: CharacterListEvent) {
        when (event) {
            else -> {}
        }
    }

}