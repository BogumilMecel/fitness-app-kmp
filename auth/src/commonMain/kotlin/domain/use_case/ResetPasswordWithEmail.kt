package domain.use_case

import domain.repository.AuthRepository
import utils.Resource

class ResetPasswordWithEmail(
    private val authRepository: AuthRepository,
//    private val validateAuthDataUseCase: ValidateAuthDataUseCase
){

//    suspend operator fun invoke(email: String): Resource<Boolean> {
//        return if (validateAuthDataUseCase())
//
//        return repository.sendPasswordResetEmail(email)
//    }
}