package presentation.base

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.services.SettingsService
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import navigation.domain.NavigationAction
import navigation.domain.NavigatorService
import navigation.domain.SnackbarService
import navigation.presentation.Route
import org.koin.core.component.KoinComponent
import org.koin.core.component.inject

open class BaseModel : ViewModel(), KoinComponent {

    protected val settingsService by inject<SettingsService>()
    protected val navigatorService by inject<NavigatorService>()
    protected val snackbarService by inject<SnackbarService>()

    open fun onBackPressed(withPopUp: Boolean = false) {
        navigateBack(withPopUp = withPopUp)
    }

    protected fun setBackResult(result: Any) {
        viewModelScope.launch {
            navigatorService.backResult.send(result)
        }
    }

    protected fun navigateTo(
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

    protected inline fun <R> runCatchingWithSnackbarOnFailure(block: () -> R): Result<R> = runCatching(block = block)
            .onFailure { throwable ->
                throwable.printStackTrace()
                throwable.message?.let {
                    snackbarService.message.trySend(it)
                }
            }
}