package com.codigo.base.view.eventDispatcher

import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.SharedFlow
import kotlinx.coroutines.flow.asSharedFlow

object EventDispatcher {

    private val dataSharedFlow = MutableSharedFlow<MainEvent>()
    val dataFlow: SharedFlow<MainEvent>
        get() = dataSharedFlow
    suspend fun dispatchEvent(event: MainEvent) = dataSharedFlow.emit(event)
}

sealed class MainEvent {
    class ToggleIndicator(val flag:Boolean) : MainEvent()
    class UpdateProgressValue(val progressValue: Int) : MainEvent()
}