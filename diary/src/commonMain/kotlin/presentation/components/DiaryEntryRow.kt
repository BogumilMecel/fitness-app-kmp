package presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.calories_short_name
import com.gmail.bogumilmecel2.diary.composeResources.carbohydrates_short_name
import com.gmail.bogumilmecel2.diary.composeResources.fat_short_name
import com.gmail.bogumilmecel2.diary.composeResources.protein_short_name
import components.HorizontalSpacer
import models.DiaryItem
import models.ProductDiaryEntry
import models.RecipeDiaryEntry
import org.jetbrains.compose.resources.stringResource
import theme.FitnessAppTheme
import utils.string.FormatTwoValues
import utils.string.formatTwoValues

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiaryEntryRow(
    modifier: Modifier = Modifier,
    diaryItem: DiaryItem,
    onItemClicked: () -> Unit,
    onItemLongClick: () -> Unit
) {
    Box(
        modifier = modifier
            .fillMaxWidth()
            .combinedClickable(onClick = onItemClicked, onLongClick = onItemLongClick)
            .background(FitnessAppTheme.colors.backgroundTertiary),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.SpaceBetween,
            modifier = Modifier
                .fillMaxWidth()
                .padding(bottom = 16.dp, top = 16.dp)
        ) {
            Column {
                Row(
                    modifier = Modifier
                        .padding(horizontal = 15.dp),
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = when (diaryItem) {
                            is ProductDiaryEntry -> diaryItem.productName
                            is RecipeDiaryEntry -> diaryItem.recipeName
                            else -> ""
                        },
                        style = FitnessAppTheme.typography.bodyLarge
                    )

                    Text(
                        text = "(${diaryItem.getDisplayValue()})",
                        style = FitnessAppTheme.typography.bodyMedium,
                        color = FitnessAppTheme.colors.contentSecondary,
                        modifier = Modifier.padding(start = 4.dp)
                    )
                }

                HorizontalSpacer(size = 4.dp)

                Row(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(
                        text = formatTwoValues(
                            value1 = stringResource(Res.string.calories_short_name),
                            value2 = diaryItem.nutritionValues.calories.toString(),
                            format = FormatTwoValues.SEMICOLON
                        ),
                        style = FitnessAppTheme.typography.bodyMedium,
                        color = FitnessAppTheme.colors.contentSecondary,
                    )

                    Text(
                        text = formatTwoValues(
                            value1 = stringResource(Res.string.carbohydrates_short_name),
                            value2 = diaryItem.nutritionValues.carbohydrates.toString(),
                            format = FormatTwoValues.SEMICOLON
                        ),
                        style = FitnessAppTheme.typography.bodyMedium,
                        color = FitnessAppTheme.colors.contentSecondary
                    )

                    Text(
                        text = formatTwoValues(
                            value1 = stringResource(Res.string.protein_short_name),
                            value2 = diaryItem.nutritionValues.protein.toString(),
                            format = FormatTwoValues.SEMICOLON
                        ),
                        style = FitnessAppTheme.typography.bodyMedium,
                        color = FitnessAppTheme.colors.contentSecondary
                    )

                    Text(
                        text = formatTwoValues(
                            value1 = stringResource(Res.string.fat_short_name),
                            value2 = diaryItem.nutritionValues.fat.toString(),
                            format = FormatTwoValues.SEMICOLON
                        ),
                        style = FitnessAppTheme.typography.bodyMedium,
                        color = FitnessAppTheme.colors.contentSecondary
                    )
                }
            }
        }
    }
}