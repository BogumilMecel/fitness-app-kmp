package domain.use_case

import domain.model.AuthRequest
import domain.repository.AuthRepository
import utils.Resource
import utils.copyType

class LogInUserUseCase(
    private val authRepository: AuthRepository,
    private val validateEmailUseCase: ValidateEmailUseCase,
    private val validatePasswordUseCase: ValidatePasswordUseCase
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

        return when (resource) {
            is Resource.Success -> Resource.Error("not implemented yet, token: ${resource.data.token}")
            is Resource.Error -> resource.copyType()
        }
    }
}