package domain.model

import androidx.compose.runtime.Composable
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.meal_name_breakfast
import com.gmail.bogumilmecel2.diary.composeResources.meal_name_dinner
import com.gmail.bogumilmecel2.diary.composeResources.meal_name_lunch
import com.gmail.bogumilmecel2.diary.composeResources.meal_name_supper
import kotlinx.serialization.Serializable
import org.jetbrains.compose.resources.stringResource

@Serializable
enum class MealName {
    BREAKFAST, LUNCH, DINNER, SUPPER;

    val displayName: String
        @Composable
        get() = when (this) {
            BREAKFAST -> stringResource(Res.string.meal_name_breakfast)
            LUNCH -> stringResource(Res.string.meal_name_lunch)
            DINNER -> stringResource(Res.string.meal_name_dinner)
            SUPPER -> stringResource(Res.string.meal_name_supper)
        }
}