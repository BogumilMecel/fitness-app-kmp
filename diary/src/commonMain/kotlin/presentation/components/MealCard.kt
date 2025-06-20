package presentation.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AddCircle
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.calories
import com.gmail.bogumilmecel2.diary.composeResources.carbs
import com.gmail.bogumilmecel2.diary.composeResources.fat
import com.gmail.bogumilmecel2.diary.composeResources.protein
import components.FitnessAppCard
import components.HorizontalSpacer
import domain.model.DiaryItem
import domain.model.MealName
import domain.model.NutritionValues
import org.jetbrains.compose.resources.stringResource
import theme.FitnessAppTheme

@Composable
fun MealCard(
    modifier: Modifier = Modifier,
    mealName: MealName,
    diaryEntries: List<DiaryItem>,
    nutritionValues: NutritionValues,
    wantedNutritionValues: NutritionValues,
    onAddClicked: () -> Unit,
    onDiaryEntryClicked: (DiaryItem) -> Unit,
    onDiaryEntryLongClicked: (DiaryItem) -> Unit,
) {
    FitnessAppCard(modifier = modifier.padding(horizontal = 16.dp, vertical = 6.dp)) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp)
        ) {

            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(start = 16.dp),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = Alignment.CenterVertically
            ) {
                Text(
                    text = mealName.displayName,
                    style = FitnessAppTheme.typography.headlineSmall,
                    color = FitnessAppTheme.colors.contentPrimary,
                )

                Row {
//                    Icon(
//                        imageVector = Icons.Default.Edit,
//                        contentDescription = null,
//                        modifier = Modifier
//                            .clickable {}
//                            .padding(10.dp)
//                            .size(32.dp),
//                        tint = OrangeYellow1)

                    Icon(
                        imageVector = Icons.Default.AddCircle,
                        contentDescription = null,
                        modifier = Modifier
                            .clickable(onClick = onAddClicked)
                            .padding(10.dp)
                            .size(32.dp),
                        tint = FitnessAppTheme.colors.primary,
                    )
                }
            }

            diaryEntries.forEach {
                DiaryEntryRow(
                    diaryItem = it,
                    onItemClicked = { onDiaryEntryClicked(it) },
                    onItemLongClick = { onDiaryEntryLongClicked(it) }
                )
            }

            HorizontalSpacer(size = 16.dp)

            Row(
                horizontalArrangement = Arrangement.spacedBy(32.dp),
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(end = 16.dp, start = 16.dp)
            ) {
                MealCardNutritionItem(
                    currentValue = nutritionValues.calories,
                    wantedValue = wantedNutritionValues.calories,
                    name = stringResource(Res.string.calories),
                    modifier = Modifier.weight(1f)
                )

                MealCardNutritionItem(
                    currentValue = nutritionValues.carbohydrates.toInt(),
                    wantedValue = wantedNutritionValues.carbohydrates.toInt(),
                    name = stringResource(Res.string.carbs),
                    modifier = Modifier.weight(1f)
                )

                MealCardNutritionItem(
                    currentValue = nutritionValues.protein.toInt(),
                    wantedValue = wantedNutritionValues.protein.toInt(),
                    name = stringResource(Res.string.protein),
                    modifier = Modifier.weight(1f)
                )

                MealCardNutritionItem(
                    currentValue = nutritionValues.fat.toInt(),
                    wantedValue = wantedNutritionValues.fat.toInt(),
                    name = stringResource(Res.string.fat),
                    modifier = Modifier.weight(1f)
                )
            }
        }
    }
}