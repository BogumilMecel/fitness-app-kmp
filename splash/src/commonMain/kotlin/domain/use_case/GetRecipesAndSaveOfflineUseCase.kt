package domain.use_case

import domain.services.SettingsService
import kotlinx.coroutines.flow.firstOrNull
import repository.DiaryRepository
import utils.Resource

class GetRecipesAndSaveOfflineUseCase(
    private val settingsService: SettingsService,
    private val diaryRepository: DiaryRepository
) {
    suspend operator fun invoke(): Resource<Unit> {
        val latestOfflineRecipe = diaryRepository.getOfflineRecipes(
            userId = settingsService.getUser().firstOrNull()?.id,
        ).firstOrNull()

        val userRecipes = diaryRepository.getUserRecipes(
            latestDateTime = latestOfflineRecipe?.creationDateTime
        ).data ?: return Resource.Error()

        return Resource.Success(diaryRepository.insertOfflineRecipes(userRecipes))
    }
}