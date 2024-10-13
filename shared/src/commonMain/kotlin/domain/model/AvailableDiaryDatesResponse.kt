package domain.model

import kotlinx.datetime.LocalDate
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailableDiaryDatesResponse(
    @SerialName("available_dates")
    val availableDates: List<LocalDate>,

    @SerialName("available_dates_count")
    val availableDaysCount: Int,
)