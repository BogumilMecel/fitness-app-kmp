package di

import data.api.AuthApiClient
import data.repository.AuthRepositoryImp
import domain.repository.AuthRepository
import domain.use_case.LogInUserUseCase
import domain.use_case.LoginUseCases
import domain.use_case.RegisterUseCases
import domain.use_case.RegisterUserUseCase
import domain.use_case.ValidateEmailUseCase
import domain.use_case.ValidatePasswordUseCase
import domain.use_case.ValidateUsernameUseCase
import org.koin.core.module.dsl.viewModel
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
        LogInUserUseCase(
            authRepository = get(),
            settingsService = get()
        )
    }
    viewModel {
        LoginScreenModel(
            loginUseCases = LoginUseCases(
                logInUserUseCase = get(),
                validateEmailUseCase = get(),
                validatePasswordUseCase = get()
            ),
        )
    }
    viewModel {
        RegisterScreenModel(
            registerUseCases = RegisterUseCases(
                validatePasswordUseCase = get(),
                validateEmailUseCase = get(),
                validateUsernameUseCase = ValidateUsernameUseCase(resourcesService = get()),
                registerUserUseCase = RegisterUserUseCase(
                    authRepository = get(),
                    logInUserUseCase = get()
                )
            )
        )
    }
    viewModel { AuthNavigationModel() }
}