package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import models.NutritionValues

@Serializable
data class User(
    @SerialName("id")
    val id: String,

    @SerialName("email")
    val email: String,

    @SerialName("username")
    val username: String,

    @SerialName("nutrition_values")
    val nutritionValues: NutritionValues? = null,

    @SerialName("has_information")
    val hasInformation: Boolean = false,

    @SerialName("log_streak")
    val logStreak: Int,

    @SerialName("latest_weight_entry")
    val latestWeightEntry: WeightEntry? = null,

    @SerialName("weight_progress")
    val weightProgress: Double? = null,

    @SerialName("ask_for_weight_daily")
    val askForWeightDaily: Boolean? = null,
)