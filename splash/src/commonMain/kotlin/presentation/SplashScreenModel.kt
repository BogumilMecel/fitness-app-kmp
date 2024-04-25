package presentation

import cafe.adriel.voyager.core.model.screenModelScope
import domain.use_case.AuthenticateUserUseCase
import domain.use_case.Result
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.navigation.SharedScreen

class SplashScreenModel (
    authenticateUserUseCase: AuthenticateUserUseCase
): BaseModel(), KoinComponent {

    init {
        screenModelScope.launch {
            when(authenticateUserUseCase()) {
                Result.NavigateToLogin -> {
                    navigateToSharedScreen(screen = SharedScreen.AuthNavigationScreen)
                }
                Result.NavigateToIntroduction -> {
                    navigateToSharedScreen(screen = SharedScreen.AuthNavigationScreen)
                }
                Result.NavigateToMainScreen -> {
                    navigateToSharedScreen(screen = SharedScreen.TabNavigatorScreen)
                }
            }
        }
    }
}