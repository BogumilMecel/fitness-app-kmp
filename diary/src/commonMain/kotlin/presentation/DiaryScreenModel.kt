package presentation

import date.getCurrentDate
import domain.use_case.CreateAvailableDiaryDatesUseCase
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.datetime.LocalDate
import presentation.base.BaseModel

class DiaryScreenModel(
    createAvailableDiaryDatesUseCase: CreateAvailableDiaryDatesUseCase,
) : BaseModel() {

    val availableDates = createAvailableDiaryDatesUseCase()
    val selectedDate = MutableStateFlow(getCurrentDate())

    fun onDateSelected(date: LocalDate) {
        selectedDate.value = date
    }
}