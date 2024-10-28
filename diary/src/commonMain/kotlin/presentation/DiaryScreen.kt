package presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.unit.dp
import cafe.adriel.voyager.core.screen.Screen
import components.HorizontalSpacer
import presentation.components.CalendarItem
import compose.getScreenWidth
import date.getShortName
import domain.model.MealName
import domain.model.NutritionValues
import presentation.components.MealCard

class DiaryScreen : Screen {

    @Composable
    override fun Content() {
        ModelLayout<DiaryScreenModel> {
            val selectedDate by selectedDate.collectAsState()
            val breakfast by breakfast.collectAsState()
            val breakfastNutritionValues by breakfastNutritionValues.collectAsState()
            val lunch by lunch.collectAsState()
            val lunchNutritionValues by lunchNutritionValues.collectAsState()
            val dinner by dinner.collectAsState()
            val dinnerNutritionValues by dinnerNutritionValues.collectAsState()
            val supper by supper.collectAsState()
            val supperNutritionValues by supperNutritionValues.collectAsState()

            Column(modifier = Modifier.fillMaxSize()) {
                HorizontalSpacer(size = 4.dp)

                LazyRow(
                    state = rememberLazyListState(
                        initialFirstVisibleItemIndex = availableDates.size / 2,
                        initialFirstVisibleItemScrollOffset = with(LocalDensity.current) {
                            // get negative screen width in px - 4px for padding in 29 elements
                            // + 2 * 2px for horizontal content padding and divide it by half
                            -((getScreenWidth().roundToPx() - 4 * (availableDates.size) + 1) / 2)
                        }
                    ),
                    horizontalArrangement = Arrangement.spacedBy(4.dp),
                    contentPadding = PaddingValues(start = 2.dp, end = 2.dp, top = 8.dp)
                ) {
                    items(items = availableDates) { date ->
                        CalendarItem(
                            dayOfWeek = date.dayOfWeek.getShortName(),
                            dayOfMonth = date.dayOfMonth.toString(),
                            selected = selectedDate == date,
                            onClick = {
                                onDateSelected(date)
                            }
                        )
                    }
                }

                HorizontalSpacer(size = 16.dp)

                MealName.entries.forEach { mealName ->
                    when (mealName) {
                        MealName.BREAKFAST -> breakfast to breakfastNutritionValues
                        MealName.LUNCH -> lunch to lunchNutritionValues
                        MealName.DINNER -> dinner to dinnerNutritionValues
                        MealName.SUPPER -> supper to supperNutritionValues
                    }.let { (meal, nutritionValues) ->
                        MealCard(
                            modifier = Modifier,
                            mealName = mealName,
                            diaryEntries = meal,
                            nutritionValues = nutritionValues,
                            // TODO: Replace with total nutrition values
                            wantedNutritionValues = NutritionValues(
                                calories = 2000,
                                carbohydrates = 200.0,
                                protein = 200.0,
                                fat = 90.0
                            ),
                            onAddClicked = {
                                onAddClicked(mealName = mealName)
                            },
                            onDiaryEntryClicked = {},
                            onDiaryEntryLongClicked = {}
                        )
                    }
                }
            }
        }
    }
}