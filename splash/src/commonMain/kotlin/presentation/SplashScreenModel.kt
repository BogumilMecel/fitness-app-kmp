package presentation

import androidx.lifecycle.viewModelScope
import domain.use_case.AuthenticateUserUseCase
import domain.use_case.Result
import kotlinx.coroutines.launch
import org.koin.core.component.KoinComponent
import presentation.base.BaseModel
import presentation.navigation.SharedScreen

class SplashScreenModel (
    authenticateUserUseCase: AuthenticateUserUseCase
): BaseModel(), KoinComponent {

    init {
        viewModelScope.launch {
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