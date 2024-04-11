package di

import data.api.AuthApiClient
import data.repository.AuthRepositoryImp
import domain.repository.AuthRepository
import domain.use_case.LogInUserUseCase
import domain.use_case.ValidateEmailUseCase
import domain.use_case.ValidatePasswordUseCase
import org.koin.dsl.module
import presentation.login.LoginScreenModel
import presentation.navigation_screen.AuthNavigationModel
import presentation.register.RegisterScreenModel

val authModule = module {
    single { AuthApiClient(httpClient = get()) }
    single<AuthRepository> { AuthRepositoryImp(authApiClient = get()) }
    single { ValidateEmailUseCase(resourceProvider = get()) }
    single { ValidatePasswordUseCase(resourceProvider = get()) }
    factory {
        LoginScreenModel(
            resourceProvider = get(),
            logInUserUseCase = LogInUserUseCase(
                authRepository = get(),
                validateEmailUseCase = get(),
                validatePasswordUseCase = get()
            )
        )
    }
    factory { RegisterScreenModel() }
    factory { AuthNavigationModel() }

}