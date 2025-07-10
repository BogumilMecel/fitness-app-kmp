package domain.use_case

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.datetime.LocalDate
import models.DiaryItem
import repository.DiaryRepository

class GetOfflineDiaryEntriesUseCase(private val diaryRepository: DiaryRepository) {
    operator fun invoke(date: LocalDate): Flow<List<DiaryItem>> {
        return combine(
            flow = diaryRepository.getOfflineProductDiaryEntries(date = date),
            flow2 = diaryRepository.getOfflineRecipeDiaryEntries(date = date),
        ) { productDiaryEntries, recipeDiaryEntries -> productDiaryEntries + recipeDiaryEntries }
    }
}