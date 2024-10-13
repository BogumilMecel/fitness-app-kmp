package presentation

import date.getCurrentDate
import domain.model.AvailableDiaryDatesResponse
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.filterNotNull
import kotlinx.datetime.LocalDate
import presentation.base.BaseModel

class DiaryScreenModel : BaseModel() {

    val availableDatesResponse = settingsService
        .getAvailableDiaryDates()
        .filterNotNull()
        .stateInScreenModelScope(
            initialValue = AvailableDiaryDatesResponse(
                availableDates = emptyList(),
                availableDaysCount = 1
            )
        )

    val selectedDate = MutableStateFlow(getCurrentDate())

    fun onDateSelected(date: LocalDate) {
        selectedDate.value = date
    }
}