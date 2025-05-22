package presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import components.TextFieldData
import navigation.domain.NavigationAction
import domain.NavigatorService
import domain.services.SettingsService
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
import navigation.presentation.Route
import utils.Resource

open class BaseModel : ViewModel(), KoinComponent {

    protected val settingsService by inject<SettingsService>()
    private val navigatorService by inject<NavigatorService>()

    open fun onBackPressed() {
        navigateBack()
    }

    fun navigateTo(
        route: Route,
        withPopUp: Boolean = false
    ) {
        viewModelScope.launch {
            navigatorService.navigationAction.send(
                NavigationAction.ToScreen(
                    route = route,
                    withPopUp = withPopUp
                )
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
        viewModelScope.launch {
            navigatorService.navigationAction.send(
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
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = initialValue,
    )
}