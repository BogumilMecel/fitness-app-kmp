package domain.model

import androidx.compose.runtime.Composable
import kotlinx.serialization.Serializable

@Serializable
enum class MealName {
    BREAKFAST, LUNCH, DINNER, SUPPER;

    val displayName: String
        @Composable
        get() = when (this) {
            BREAKFAST -> "Breakfast"
            LUNCH -> "Lunch"
            DINNER -> "Dinner"
            SUPPER -> "Supper"
        }
}