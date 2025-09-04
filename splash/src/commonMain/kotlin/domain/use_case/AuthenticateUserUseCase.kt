package domain.use_case

import domain.repository.LoadingRepository
import domain.services.SettingsService

class AuthenticateUserUseCase(
    private val settingsService: SettingsService,
    private val loadingRepository: LoadingRepository,
) {
    suspend operator fun invoke(): Result {
        settingsService.getAccessToken() ?: return Result.NavigateToLogin

        val user = loadingRepository.authenticateUser().data ?: return Result.NavigateToLogin

        settingsService.setUser(user = user)

        return if (user.nutritionValues != null && user.hasInformation) {
            Result.NavigateToMainScreen
        } else {
            Result.NavigateToIntroduction
        }
    }
}

sealed interface Result {
    data object NavigateToIntroduction : Result
    data object NavigateToLogin : Result
    data object NavigateToMainScreen : Result
}