package presentation.base

import cafe.adriel.voyager.core.model.ScreenModel
import cafe.adriel.voyager.core.model.screenModelScope
import cafe.adriel.voyager.core.screen.Screen
import components.TextFieldData
import domain.services.SettingsService
import kotlinx.coroutines.channels.Channel
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import presentation.navigation.NavigationAction
import presentation.navigation.SharedScreen
import utils.Resource

open class BaseModel : ScreenModel, KoinComponent {

    protected val settingsService by inject<SettingsService>()
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

    protected fun <T> Resource<T>.handle(validationField: MutableStateFlow<TextFieldData>) {
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

    protected fun getNotNullUserFlow() = settingsService.getUser().filterNotNull()

    private fun navigateBack(withPopUp: Boolean = false) {
        screenModelScope.launch {
            navigation.send(
                NavigationAction.Back(withPopUp = withPopUp)
            )
        }
    }

    protected fun MutableStateFlow<TextFieldData>.getText() = value.text

    private fun MutableStateFlow<TextFieldData>.setError(error: String?) = update {
        it.copy(error = error)
    }

    fun MutableStateFlow<TextFieldData>.isNotError() = value.error == null

    fun MutableStateFlow<TextFieldData>.initTextField(
        initialValue: String = "",
        onValueChange: ((String) -> Unit)? = null
    ) {
        value = TextFieldData(
            text = initialValue,
            onValueChange = onValueChange ?: { newValue -> update { it.copy(text = newValue) } },
            onErrorCleared = { update { it.copy(error = null) } }
        )
    }

    protected fun <T> Flow<T>.stateInScreenModelScope(initialValue: T): StateFlow<T> = stateIn(
        scope = screenModelScope,
        started = SharingStarted.Eagerly,
        initialValue = initialValue,
    )
}