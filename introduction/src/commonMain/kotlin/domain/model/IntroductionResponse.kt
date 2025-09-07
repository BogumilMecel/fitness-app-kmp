package domain.model

import kotlinx.serialization.SerialName
import kotlinx.serialization.Serializable
import models.NutritionValues

@Serializable
data class IntroductionResponse(
    @SerialName("nutrition_values")
    val nutritionValues: NutritionValues,
) 