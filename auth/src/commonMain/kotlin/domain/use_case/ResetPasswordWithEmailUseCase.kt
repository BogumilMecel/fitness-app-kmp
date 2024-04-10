package domain.use_case

import domain.model.AuthRequest
import domain.repository.AuthRepository
import utils.Resource

class ResetPasswordWithEmailUseCase(
    private val authRepository: AuthRepository,
    private val validateEmailUseCase: ValidateEmailUseCase
){
    suspend operator fun invoke(email: String): Resource<Unit> {
        val validateEmailResource = validateEmailUseCase(email)
        if (validateEmailResource !is Resource.Success) return validateEmailResource

        return authRepository.sendPasswordResetEmail(
            authRequest = AuthRequest(email = email)
        )
    }
}