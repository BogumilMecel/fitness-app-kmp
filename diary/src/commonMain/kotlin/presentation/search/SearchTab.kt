package presentation.search

import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.search_everything
import com.gmail.bogumilmecel2.diary.composeResources.search_my_products
import com.gmail.bogumilmecel2.diary.composeResources.search_my_recipes
import org.jetbrains.compose.resources.StringResource

enum class SearchTab(val textResId: StringResource) {
    EVERYTHING(textResId = Res.string.search_everything),
    PRODUCTS(textResId = Res.string.search_my_products),
    RECIPES(textResId = Res.string.search_my_recipes)
}