package domain.use_case

data class RegisterUseCases(
    val validatePasswordUseCase: ValidatePasswordUseCase,
    val validateUsernameUseCase: ValidateUsernameUseCase,
    val validateEmailUseCase: ValidateEmailUseCase,
    val registerUserUseCase: RegisterUserUseCase
)