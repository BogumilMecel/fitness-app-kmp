package presentation

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import components.TextFieldData
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.navigation.NavigationAction
import presentation.navigation.SharedScreen
import utils.Resource

open class BaseModel : ScreenModel, KoinComponent {

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
        onSuccess: (T) -> Unit = {}
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

    protected inline fun <T> Resource<T>.handle(validationField: MutableStateFlow<TextFieldData>) {
        handle(
            onError = { validationField.setError(it.message) },
            onSuccess = { validationField.setError(null) }
        )
    }

    protected inline fun <T> Resource<T>.handle(
        finally: () -> Unit = {},
        onError: (Exception) -> Unit = {},
        onSuccess: (T) -> Unit = {}
    ) {
        when (this) {
            is Resource.Error -> {
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

    protected fun <T> Flow<T>.collectInScreenModel(initialValue: T) =
        stateIn(screenModelScope, SharingStarted.Eagerly, initialValue = initialValue)

    protected fun MutableStateFlow<TextFieldData>.setText(text: String) =
        update { it.copy(text = text) }

    protected fun MutableStateFlow<TextFieldData>.getText() = value.text

    protected fun MutableStateFlow<TextFieldData>.setError(error: String?) =
        update { it.copy(error = error) }

    protected fun MutableStateFlow<TextFieldData>.isError() = value.error != null
}