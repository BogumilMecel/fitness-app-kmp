package di

import data.api.LoadingApi
import data.repository.LoadingRepositoryImp
import domain.use_case.AuthenticateUserUseCase
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
            )
        )
    }
}