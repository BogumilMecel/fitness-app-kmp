package di

import data.api.LoadingApi
import data.repository.LoadingRepositoryImp
import domain.use_case.AuthenticateUserUseCase
import domain.use_case.GetProductDiaryAndSaveOfflineUseCase
import domain.use_case.GetProductsAndSaveOfflineUseCase
import domain.use_case.GetRecipeDiaryAndSaveOfflineUseCase
import domain.use_case.GetRecipesAndSaveOfflineUseCase
import org.koin.core.module.dsl.viewModel
import org.koin.dsl.module
import presentation.SplashScreenModel

val splashModule = module {
    viewModel {
        SplashScreenModel(
            authenticateUserUseCase = AuthenticateUserUseCase(
                settingsService = get(),
                loadingRepository = LoadingRepositoryImp(
                    loadingApi = LoadingApi(httpClient = get())
                )
            ),
            getProductsAndSaveOfflineUseCase = GetProductsAndSaveOfflineUseCase(
                diaryRepository = get(),
                settingsService = get()
            ),
            getProductDiaryAndSaveOfflineUseCase = GetProductDiaryAndSaveOfflineUseCase(
                diaryRepository = get(),
            ),
            getRecipesAndSaveOfflineUseCase = GetRecipesAndSaveOfflineUseCase(
                diaryRepository = get(),
                settingsService = get(),
            ),
            getRecipeDiaryAndSaveOfflineUseCase = GetRecipeDiaryAndSaveOfflineUseCase(
                diaryRepository = get(),
            )
        )
    }
}