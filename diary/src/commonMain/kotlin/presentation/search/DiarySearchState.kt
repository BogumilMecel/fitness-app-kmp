package presentation.search

import androidx.compose.runtime.Stable
import domain.model.MealName
import kotlinx.datetime.LocalDate
import presentation.components.DiaryItemParams

@Stable
data class DiarySearchState(
    val date: LocalDate,
    val mealName: MealName,
    val searchBarText: String = "",
    val selectedTab: SearchTab = SearchTab.EVERYTHING,
    val productsParams: List<DiaryItemParams> = emptyList(),
)