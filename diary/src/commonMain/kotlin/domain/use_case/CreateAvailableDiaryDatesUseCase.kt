package domain.use_case

import date.getCurrentDate
import domain.services.SettingsService
import kotlinx.datetime.DatePeriod
import kotlinx.datetime.DateTimeUnit
import kotlinx.datetime.LocalDate
import kotlinx.datetime.minus
import kotlinx.datetime.plus

class CreateAvailableDiaryDatesUseCase(private val settingsService: SettingsService) {
    operator fun invoke(): List<LocalDate> {
        val availableDaysCount = settingsService.getAvailableDiaryDatesCount()
        val currentDay = getCurrentDate()

        return buildList {
            currentDay.minus(DatePeriod(days = availableDaysCount / 2)).let { startingDate ->
                repeat(availableDaysCount) { dateIndex ->
                    add(startingDate.plus(value = dateIndex, unit = DateTimeUnit.DAY))
                }
            }
        }
    }
}