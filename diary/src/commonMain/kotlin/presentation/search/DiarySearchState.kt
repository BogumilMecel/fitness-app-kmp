package presentation.search

import androidx.compose.runtime.Stable
import kotlinx.datetime.LocalDate
import models.MealName
import models.Product

@Stable
data class DiarySearchState(
    val date: LocalDate,
    val mealName: MealName,
    val searchBarText: String = "",
    val selectedTab: SearchTab = SearchTab.EVERYTHING,
    val listState: ListState = ListState.Loading,
    val userProductsState: ListState = ListState.Loading,
)

sealed interface ListState {
    data object Loading: ListState
    data class Results(val items: List<Product>): ListState
}