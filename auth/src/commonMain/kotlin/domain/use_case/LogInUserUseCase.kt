package domain.use_case

import domain.model.AuthRequest
import domain.services.SettingsService
import domain.repository.AuthRepository
import utils.Resource

class LogInUserUseCase(
    private val authRepository: AuthRepository,
    private val settingsService: SettingsService
) {
    suspend operator fun invoke(email: String, password: String): Resource<Unit> {
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