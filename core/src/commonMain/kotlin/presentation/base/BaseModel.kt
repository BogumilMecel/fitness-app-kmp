package presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import components.TextFieldData
import domain.services.SettingsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import navigation.domain.NavigationAction
import navigation.domain.NavigatorService
import navigation.presentation.Route
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject
import utils.Resource
import utils.flow.setError

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

    protected fun <T> Flow<T>.stateInScreenModelScope(initialValue: T): StateFlow<T> = stateIn(
        scope = viewModelScope,
        started = SharingStarted.Eagerly,
        initialValue = initialValue,
    )
}