package presentation.search

import androidx.compose.runtime.Composable
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.search_everything
import com.gmail.bogumilmecel2.diary.composeResources.search_my_products
import com.gmail.bogumilmecel2.diary.composeResources.search_my_recipes
import org.jetbrains.compose.resources.stringResource

enum class SearchTab {
    EVERYTHING,
    PRODUCTS,
    RECIPES;

    val displayValue
        @Composable
        get() = stringResource(
            resource = when (this) {
                EVERYTHING -> Res.string.search_everything
                PRODUCTS -> Res.string.search_my_products
                RECIPES -> Res.string.search_my_recipes
            }
        )
}