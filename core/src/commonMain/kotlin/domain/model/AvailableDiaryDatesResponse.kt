package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class AvailableDiaryDatesResponse(
    @SerialName("available_dates_count")
    val availableDaysCount: Int,
)