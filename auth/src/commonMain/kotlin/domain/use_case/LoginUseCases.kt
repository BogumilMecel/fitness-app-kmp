package domain.use_case

data class LoginUseCases(
    val logInUserUseCase: LogInUserUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val validatePasswordUseCase: ValidatePasswordUseCase
)