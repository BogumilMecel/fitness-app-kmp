package presentation

import androidx.lifecycle.viewModelScope
import domain.use_case.AuthenticateUserUseCase
import domain.use_case.GetProductDiaryAndSaveOfflineUseCase
import domain.use_case.GetProductsAndSaveOfflineUseCase
import domain.use_case.GetRecipeDiaryAndSaveOfflineUseCase
import domain.use_case.GetRecipesAndSaveOfflineUseCase
import domain.use_case.Result
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.IO
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch
import navigation.presentation.Route
import org.koin.core.component.KoinComponent
import presentation.base.BaseModel

class SplashScreenModel (
    authenticateUserUseCase: AuthenticateUserUseCase,
    getProductDiaryAndSaveOfflineUseCase: GetProductDiaryAndSaveOfflineUseCase,
    getRecipeDiaryAndSaveOfflineUseCase: GetRecipeDiaryAndSaveOfflineUseCase,
    getProductsAndSaveOfflineUseCase: GetProductsAndSaveOfflineUseCase,
    getRecipesAndSaveOfflineUseCase: GetRecipesAndSaveOfflineUseCase,
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
                    viewModelScope.launch(Dispatchers.IO) {
                        val productDiaryJob = async { getProductDiaryAndSaveOfflineUseCase() }
                        val recipeDiaryJob = async { getRecipeDiaryAndSaveOfflineUseCase() }
                        val productJob = async { getProductsAndSaveOfflineUseCase() }
                        val recipeJob = async { getRecipesAndSaveOfflineUseCase() }

                        awaitAll(productDiaryJob, recipeDiaryJob, productJob, recipeJob)

                        navigateTo(
                            route = Route.BottomNavigation.Summary,
                            popUpTo = Route.Splash,
                        )
                    }
                }
            }
        }
    }
}