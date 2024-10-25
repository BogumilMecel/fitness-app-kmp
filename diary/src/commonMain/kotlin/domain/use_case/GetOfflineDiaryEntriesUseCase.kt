package domain.use_case

import domain.model.DiaryItem
import domain.repository.DiaryRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.combine
import kotlinx.datetime.LocalDate

class GetOfflineDiaryEntriesUseCase(private val diaryRepository: DiaryRepository) {
    suspend operator fun invoke(date: LocalDate): Flow<List<DiaryItem>> {
        return combine(
            flow = diaryRepository.getOfflineProductDiaryEntries(date = date),
            flow2 = diaryRepository.getOfflineRecipeDiaryEntries(date = date),
        ) { productDiaryEntries, recipeDiaryEntries -> productDiaryEntries + recipeDiaryEntries }
    }
}