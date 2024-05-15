package di

import data.api.LoadingApi
import data.repository.LoadingRepositoryImp
import domain.repository.LoadingRepository
import domain.use_case.AuthenticateUserUseCase
import domain.use_case.GetDiaryCacheUseCase
import org.koin.dsl.module
import presentation.SplashScreenModel

val splashModule = module {
    single<LoadingRepository> {
        LoadingRepositoryImp(
            loadingApi = LoadingApi(httpClient = get())
        )
    }
    factory {
        SplashScreenModel(
            authenticateUserUseCase = AuthenticateUserUseCase(
                settingsService = get(),
                loadingRepository = get()
            ),
            getDiaryCacheUseCase = GetDiaryCacheUseCase(loadingRepository = get())
        )
    }
}