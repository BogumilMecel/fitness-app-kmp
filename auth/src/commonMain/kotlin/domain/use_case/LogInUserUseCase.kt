package domain.use_case

import domain.model.AuthRequest
import domain.services.SettingsService
import domain.repository.AuthRepository
import utils.Resource

class LogInUserUseCase(
    private val authRepository: AuthRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase,
    private val settingsService: SettingsService
) {
    suspend operator fun invoke(email: String, password: String): Resource<Unit> {
        val emailValidationResource = validateEmailUseCase(email = email)
        if (emailValidationResource is Resource.Error) return emailValidationResource

        val passwordValidationResource = validatePasswordUseCase(password = password)
        if (passwordValidationResource is Resource.Error) return passwordValidationResource

        val resource = authRepository.logInUser(
            authRequest = AuthRequest(
                email = email,
                password = password
            )
        )

        resource.data?.token?.let {
            settingsService.saveAccessToken(it)
            return Resource.Success(Unit)
        }

        return Resource.Error()
    }
}