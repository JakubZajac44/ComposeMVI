package com.escola.compose.resource.viewmodel

sealed interface Reducer {

    interface ViewState: Reducer

    interface ViewEvent: Reducer

    interface ViewEffect: Reducer

}