package com.escola.compose.character.presentation.character_list

import androidx.lifecycle.viewModelScope
import androidx.paging.cachedIn
import com.escola.compose.character.domain.use_case.GetCharacterListUseCase
import com.escola.compose.resource.viewmodel.BaseViewModel
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class CharacterListViewModel @Inject constructor(
    getCharacterListUseCase: GetCharacterListUseCase,
) : BaseViewModel<CharacterListState, CharacterListEvent, CharacterListEffect>(
    initialState = CharacterListState()
) {

    var characterListFlow = getCharacterListUseCase.invoke().cachedIn(viewModelScope)


    override fun onEvent(event: CharacterListEvent) {
        when (event) {
            else -> {}
        }
    }

}