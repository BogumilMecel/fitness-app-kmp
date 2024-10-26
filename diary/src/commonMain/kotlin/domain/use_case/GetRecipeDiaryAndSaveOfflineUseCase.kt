package domain.use_case

import domain.repository.DiaryRepository
import kotlinx.coroutines.flow.firstOrNull
import utils.Resource

class GetRecipeDiaryAndSaveOfflineUseCase(private val diaryRepository: DiaryRepository) {
    suspend operator fun invoke(): Resource<Unit> {
        val latestOfflineRecipeDiaryEntry = diaryRepository.getOfflineRecipeDiaryEntries(
            limit = 1,
        ).firstOrNull() ?: return Resource.Error()

        val userRecipeDiaryEntries = diaryRepository.getRecipeDiaryEntries(
            latestDateTime = latestOfflineRecipeDiaryEntry.firstOrNull()?.changeDateTime
        ).data ?: return Resource.Error()

        return Resource.Success(diaryRepository.insertOfflineRecipeDiaryEntries(userRecipeDiaryEntries))
    }
}