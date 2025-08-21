package presentation

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.rememberPagerState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.VerticalSpacer
import models.MealName
import models.NutritionValues
import org.koin.compose.viewmodel.koinViewModel
import presentation.components.CalendarItem
import presentation.components.MealCard
import utils.date.getShortName
import utils.date.weekDatesWithOffset

@Composable
fun DiaryScreen(viewModel: DiaryScreenModel = koinViewModel()) = with(viewModel) {
    val selectedDate by selectedDate.collectAsState()
    val breakfast by breakfast.collectAsState()
    val breakfastNutritionValues by breakfastNutritionValues.collectAsState()
    val lunch by lunch.collectAsState()
    val lunchNutritionValues by lunchNutritionValues.collectAsState()
    val dinner by dinner.collectAsState()
    val dinnerNutritionValues by dinnerNutritionValues.collectAsState()
    val supper by supper.collectAsState()
    val supperNutritionValues by supperNutritionValues.collectAsState()

    val datesPagerState = rememberPagerState(initialPage = Int.MAX_VALUE / 2) {
        Int.MAX_VALUE
    }

    Column(modifier = Modifier.fillMaxSize()) { 
        VerticalSpacer(size = 8.dp)

        HorizontalPager(
            state = datesPagerState,
            modifier = Modifier.fillMaxWidth(),
        ) { page ->
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp),
            ) {
                weekDatesWithOffset(offsetWeeks = page - Int.MAX_VALUE / 2).forEachIndexed { index, date ->
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
        }

        VerticalSpacer(size = 16.dp)

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