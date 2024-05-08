package domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeightEntry(
    @SerialName("value")
    val value: Double,

    @SerialName("creation_date_time")
    val creationDateTime: LocalDateTime,

    @SerialName("date")
    val date: LocalDate
)