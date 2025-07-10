package domain.use_case

import domain.services.SettingsService
import kotlinx.coroutines.flow.firstOrNull
import repository.DiaryRepository
import utils.Resource

class GetProductsAndSaveOfflineUseCase(
    private val diaryRepository: DiaryRepository,
    private val settingsService: SettingsService,
) {
    suspend operator fun invoke(): Resource<Unit> {
        val latestOfflineProduct = diaryRepository.getOfflineProducts(
            userId = settingsService.getUser().firstOrNull()?.id,
            limit = 1,
        ).firstOrNull() ?: return Resource.Error()

        val userProducts = diaryRepository.getUserProducts(
            latestDateTime = latestOfflineProduct.creationDateTime
        ).data ?: return Resource.Error()

        return Resource.Success(diaryRepository.insertOfflineProducts(userProducts))
    }
}