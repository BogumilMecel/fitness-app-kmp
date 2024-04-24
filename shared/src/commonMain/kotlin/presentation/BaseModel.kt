package presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.launch
import presentation.navigation.NavigationAction
import presentation.navigation.SharedScreen
import utils.Resource

open class BaseModel : ScreenModel {

    val navigation = Channel<NavigationAction>()

    open fun onBackPressed() {
        navigateBack()
    }

    fun navigateTo(
        screen: Screen,
        withPopUp: Boolean = false
    ) {
        screenModelScope.launch {
            navigation.send(
                NavigationAction.ToScreen(
                    screen = screen,
                    withPopUp = withPopUp
                )
            )
        }
    }

    fun navigateToSharedScreen(screen: SharedScreen) {
        screenModelScope.launch {
            navigation.send(
                NavigationAction.ToSharedScreen(screen = screen)
            )
        }
    }

    protected inline fun <T> Resource<T>.handle(
        showSnackbar: Boolean = true,
        finally: () -> Unit = {},
        onError: (Exception) -> Unit = {},
        onSuccess: (T) -> Unit
    ) {
        when (this) {
            is Resource.Error -> {
                if (showSnackbar) {
                    // TODO: Add snackbar when ready
                }
                onError(exception)
            }

            is Resource.Success -> {
                onSuccess(data)
            }
        }
        finally()
    }

    private fun navigateBack(withPopUp: Boolean = false) {
        screenModelScope.launch {
            navigation.send(
                NavigationAction.Back(withPopUp = withPopUp)
            )
        }
    }
}