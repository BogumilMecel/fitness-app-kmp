package domain.use_case

import domain.repository.DiaryRepository
import utils.Resource

class GetRecipeDiaryAndSaveOfflineUseCase(private val diaryRepository: DiaryRepository) {
    suspend operator fun invoke(): Resource<Unit> {
        val latestOfflineRecipeDiaryEntry = diaryRepository.getOfflineRecipeDiaryEntries(
            limit = 1,
        ).data ?: return Resource.Error()

        val userRecipeDiaryEntries = diaryRepository.getRecipeDiaryEntries(
            latestDateTime = latestOfflineRecipeDiaryEntry.firstOrNull()?.changeDateTime
        ).data ?: return Resource.Error()

        return diaryRepository.insertOfflineRecipeDiaryEntries(userRecipeDiaryEntries)
    }
}