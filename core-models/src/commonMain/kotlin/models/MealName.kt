package models

import androidx.compose.runtime.Composable
import com.gmail.bogumilmecel2.`core-models`.composeResources.Res
import com.gmail.bogumilmecel2.`core-models`.composeResources.meal_brakfast
import com.gmail.bogumilmecel2.`core-models`.composeResources.meal_dinner
import com.gmail.bogumilmecel2.`core-models`.composeResources.meal_lunch
import com.gmail.bogumilmecel2.`core-models`.composeResources.meal_supper
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.StringResource
import org.jetbrains.compose.resources.stringResource

@Serializable
enum class MealName {
    BREAKFAST, LUNCH, DINNER, SUPPER;

    val displayName: String
        @Composable
        get() = stringResource(resource)

    val resource: StringResource
        get() = when (this) {
            BREAKFAST -> Res.string.meal_brakfast
            LUNCH -> Res.string.meal_lunch
            DINNER -> Res.string.meal_dinner
            SUPPER -> Res.string.meal_supper
        }
}