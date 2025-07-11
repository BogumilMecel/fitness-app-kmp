package preview

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import utils.date.getCurrentDate
import models.MealName
import presentation.search.DiarySearchScreen
import presentation.search.DiarySearchState
import presentation.search.ListState
import presentation.search.SearchTab
import theme.FitnessAppTheme

@Composable
@Preview
private fun DiarySearchPreviewLight() {
    PreviewContent(darkTheme = false)
}

@Composable
@Preview
private fun DiarySearchPreviewDark() {
    PreviewContent(darkTheme = true)
}

@Composable
private fun PreviewContent(darkTheme: Boolean) {
    FitnessAppTheme(darkTheme = darkTheme) {
        DiarySearchScreen(
            state = DiarySearchState(
                date = getCurrentDate(),
                mealName = MealName.BREAKFAST,
                searchBarText = "Rice",
                selectedTab = SearchTab.EVERYTHING,
                listState = ListState.Loading,
                userProductsState = ListState.Loading,
            ),
            onEvent = {}
        )
    }
}