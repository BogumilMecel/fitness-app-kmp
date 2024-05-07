package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable

@Serializable
data class User(
    @SerialName("id")
    val id: String,

    @SerialName("email")
    val email: String,

    @SerialName("username")
    val username: String,

    @SerialName("nutrition_values")
    val nutritionValues: NutritionValues?,

    @SerialName("user_information")
    val userInformation: UserInformation?,

    @SerialName("log_streak")
    val logStreak: Int,

    @SerialName("weight_progress")
    val weightProgress: Double?,

    @SerialName("ask_for_weight_daily")
    val askForWeightDaily: Boolean,
)