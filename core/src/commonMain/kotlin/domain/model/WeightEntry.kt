package domain.model

import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class WeightEntry(
    @SerialName("value")
    val value: Double? = null,

    @SerialName("creation_date_time")
    val creationDateTime: LocalDateTime? = null,

    @SerialName("utils/date")
    val date: LocalDate? = null
)