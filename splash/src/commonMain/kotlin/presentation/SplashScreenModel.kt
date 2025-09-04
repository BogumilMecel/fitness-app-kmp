package presentation

import androidx.lifecycle.viewModelScope
import domain.use_case.AuthenticateUserUseCase
import domain.use_case.Result
import kotlinx.coroutines.launch
import navigation.presentation.Route
import org.koin.core.component.KoinComponent
import presentation.base.BaseModel

class SplashScreenModel (
    authenticateUserUseCase: AuthenticateUserUseCase
): BaseModel(), KoinComponent {
    init {
        viewModelScope.launch {
            when(authenticateUserUseCase()) {
                Result.NavigateToLogin -> {
                    navigateTo(
                        route = Route.Login,
                        popUpTo = Route.Splash,
                    )
                }
                Result.NavigateToIntroduction -> {
                    navigateTo(
                        route = Route.Introduction,
                        popUpTo = Route.Splash,
                    )
                }
                Result.NavigateToMainScreen -> {
                    navigateTo(
                        route = Route.BottomNavigation.Summary,
                        popUpTo = Route.Splash,
                    )
                }
            }
        }
    }
}