package presentation

import utils.date.getCurrentDate
import models.DiaryItem
import models.MealName
import models.NutritionValues
import domain.use_case.CreateAvailableDiaryDatesUseCase
import domain.use_case.GetOfflineDiaryEntriesUseCase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.map
import kotlinx.datetime.LocalDate
import navigation.presentation.Route
import presentation.base.BaseModel

class DiaryScreenModel(
    createAvailableDiaryDatesUseCase: CreateAvailableDiaryDatesUseCase,
    private val getOfflineDiaryEntriesUseCase: GetOfflineDiaryEntriesUseCase
) : BaseModel() {

    val availableDates = createAvailableDiaryDatesUseCase()
    val selectedDate = MutableStateFlow(getCurrentDate())

    @OptIn(ExperimentalCoroutinesApi::class)
    private val diaryEntries = selectedDate
        .map { date -> getOfflineDiaryEntriesUseCase(date) }
        .flatMapLatest { it }

    val breakfast = diaryEntries.map { diaryEntries ->
        diaryEntries.filter { it.mealName == MealName.BREAKFAST }
    }.stateInScreenModelScope(emptyList())

    val breakfastNutritionValues = breakfast.map {
        it.sumNutritionValues()
    }.stateInScreenModelScope(NutritionValues())

    val lunch = diaryEntries.map { diaryEntries ->
        diaryEntries.filter { it.mealName == MealName.LUNCH }
    }.stateInScreenModelScope(emptyList())

    val lunchNutritionValues = lunch.map {
        it.sumNutritionValues()
    }.stateInScreenModelScope(NutritionValues())

    val dinner = diaryEntries.map { diaryEntries ->
        diaryEntries.filter { it.mealName == MealName.DINNER }
    }.stateInScreenModelScope(emptyList())

    val dinnerNutritionValues = dinner.map {
        it.sumNutritionValues()
    }.stateInScreenModelScope(NutritionValues())

    val supper = diaryEntries.map { diaryEntries ->
        diaryEntries.filter { it.mealName == MealName.SUPPER }
    }.stateInScreenModelScope(emptyList())

    val supperNutritionValues = supper.map {
        it.sumNutritionValues()
    }.stateInScreenModelScope(NutritionValues())

    fun onDateSelected(date: LocalDate) {
        selectedDate.value = date
    }

    fun onAddClicked(mealName: MealName) {
        navigateTo(
            Route.DiarySearch(
                mealName = mealName,
                date = selectedDate.value.toString()
            )
        )
    }

    private fun List<DiaryItem>.sumNutritionValues() = NutritionValues(
        calories = sumOf { it.nutritionValues.calories },
        carbohydrates = sumOf { it.nutritionValues.carbohydrates },
        protein = sumOf { it.nutritionValues.protein },
        fat = sumOf { it.nutritionValues.fat },
    )
}