package com.escola.compose.resource.viewmodel

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.receiveAsFlow

abstract class BaseViewModel<State : Reducer.ViewState, Event : Reducer.ViewEvent, Effect : Reducer.ViewEffect>(
    initialState: State,
) : ViewModel() {

    protected val _state: MutableStateFlow<State> = MutableStateFlow(initialState)
    val state: StateFlow<State>
        get() = _state.asStateFlow()

    private val _effects = Channel<Effect>(capacity = Channel.CONFLATED)
    val effect = _effects.receiveAsFlow()

    abstract fun onEvent(event: Event)

    fun sendEffect(effect: Effect) {
        _effects.trySend(effect)
    }
}