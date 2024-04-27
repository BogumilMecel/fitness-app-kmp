package di

import data.api.AuthApiClient
import data.repository.AuthRepositoryImp
import domain.repository.AuthRepository
import domain.use_case.LogInUserUseCase
import domain.use_case.LoginUseCases
import domain.use_case.ValidateEmailUseCase
import domain.use_case.ValidatePasswordUseCase
import org.koin.dsl.module
import presentation.login.LoginScreenModel
import presentation.navigation_screen.AuthNavigationModel
import presentation.register.RegisterScreenModel

val authModule = module {
    single { AuthApiClient(httpClient = get()) }
    single<AuthRepository> { AuthRepositoryImp(authApiClient = get()) }
    single { ValidateEmailUseCase(resourcesService = get()) }
    single { ValidatePasswordUseCase(resourcesService = get()) }
    factory {
        LoginScreenModel(
            loginUseCases = LoginUseCases(
                logInUserUseCase = LogInUserUseCase(
                    authRepository = get(),
                    settingsService = get()
                ),
                validateEmailUseCase = get(),
                validatePasswordUseCase = get()
            ),
        )
    }
    factory { RegisterScreenModel() }
    factory { AuthNavigationModel() }
}