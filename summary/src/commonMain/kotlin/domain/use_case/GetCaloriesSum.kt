/*
package domain.use_case

import com.gmail.bogumilmecel2.fitnessappv2.feature_diary.domain.repository.OfflineDiaryRepository
import kotlinx.datetime.LocalDate

class GetCaloriesSum(private val offlineDiaryRepository: OfflineDiaryRepository) {
    suspend operator fun invoke(date: LocalDate): Int {
        val nutritionValues = offlineDiaryRepository.getDiaryEntriesNutritionValues(date).data ?: return 0
        return nutritionValues.sumOf { it.calories }
    }
}*/
