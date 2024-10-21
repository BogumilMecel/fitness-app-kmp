package domain.use_case

import domain.repository.DiaryRepository
import utils.Resource

class GetProductDiaryAndSaveOfflineUseCase(private val diaryRepository: DiaryRepository) {
    suspend operator fun invoke(): Resource<Unit> {
        val latestOfflineProductDiaryEntry = diaryRepository.getOfflineProductDiaryEntries(
            limit = 1
        ).data ?: return Resource.Error()

        val userProductDiaryEntries = diaryRepository.getProductDiaryEntries(
            latestDateTime = latestOfflineProductDiaryEntry.firstOrNull()?.changeDateTime
        ).data ?: return Resource.Error()

        return diaryRepository.insertOfflineProductDiaryEntries(userProductDiaryEntries)
    }
}