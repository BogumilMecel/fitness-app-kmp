package utils

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch

open class BaseModel : ScreenModel {

    val navigation = Channel<Screen>()

    fun navigateTo(screen: Screen) {
        screenModelScope.launch {
            navigation.send(screen)
        }
    }
}