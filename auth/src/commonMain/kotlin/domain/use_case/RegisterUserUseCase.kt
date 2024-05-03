package domain.use_case

import domain.model.AuthRequest
import domain.repository.AuthRepository
import domain.services.SettingsService
import utils.Resource
import utils.copyType

class RegisterUserUseCase(
    private val authRepository: AuthRepository,
    private val settingsService: SettingsService
) {
    suspend operator fun invoke(
        email: String,
        password: String,
        username: String
    ): Resource<Unit> {
        val resource = authRepository.registerUser(
            authRequest = AuthRequest(
                username = username,
                email = email,
                password = password
            )
        )
        when (resource) {
            is Resource.Error -> return resource.copyType()
            is Resource.Success -> {
                resource.data.token?.let {
                    settingsService.saveAccessToken(it)
                    return Resource.Success(Unit)
                }
                return Resource.Error()
            }
        }
    }
}
