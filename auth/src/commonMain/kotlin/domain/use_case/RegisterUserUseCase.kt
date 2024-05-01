package domain.use_case

import domain.model.AuthRequest
import domain.repository.AuthRepository
import utils.Resource

class RegisterUserUseCase(
    private val authRepository: AuthRepository,
    private val logInUserUseCase: LogInUserUseCase
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        username: String
    ): Resource<Unit> {
        val registerResource = authRepository.registerUser(
            authRequest = AuthRequest(
                username = username,
                email = email,
                password = password
            )
        )

        return when (registerResource) {
            is Resource.Success -> logInUserUseCase(email = email, password = password)
            is Resource.Error -> registerResource
        }
    }
}
