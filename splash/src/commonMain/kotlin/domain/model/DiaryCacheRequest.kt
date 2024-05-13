package domain.model

import kotlinx.datetime.LocalDateTime
import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class DiaryCacheRequest (
    @SerialName("latest_product_date_time")
    val latestProductDateTime: LocalDateTime?,

    @SerialName("latest_recipe_date_time")
    val latestRecipeDateTime: LocalDateTime?,

    @SerialName("latest_product_diary_entry_date_time")
    val latestProductDiaryEntryDateTime: LocalDateTime?,

    @SerialName("latest_recipe_diary_entry_date_time")
    val latestRecipeDiaryEntryDateTime: LocalDateTime?,
)