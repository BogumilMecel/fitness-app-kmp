package presentation.product

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowDropDown
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import com.gmail.bogumilmecel2.diary.composeResources.Res
import com.gmail.bogumilmecel2.diary.composeResources.calories_short_name
import com.gmail.bogumilmecel2.diary.composeResources.carbs
import com.gmail.bogumilmecel2.diary.composeResources.fat
import com.gmail.bogumilmecel2.diary.composeResources.product_weight
import com.gmail.bogumilmecel2.diary.composeResources.protein
import components.Button
import components.ChartSlice
import components.DonutChart
import components.MediumButtonTextContent
import components.SheetTopBarWithEndButton
import components.TextField
import models.MeasurementUnit
import models.NutritionValue
import org.jetbrains.compose.resources.stringResource
import theme.FitnessAppTheme
import utils.string.PERCENTAGE
import utils.string.SEMICOLON
import utils.string.SPACE

@Composable
fun ProductScreen(
    state: ProductState,
    onEvent: (ProductEvent) -> Unit,
) {
    Column {
        SheetTopBarWithEndButton(
            title = state.productName,
            endButtonTextColor = FitnessAppTheme.colors.primary,
            endButtonText = "SAVE",
            onEndButtonClicked = {
                onEvent(ProductEvent.OnSaveClicked)
            },
            onBackPressed = {
                onEvent(ProductEvent.OnBackPressed)
            }
        )

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = 16.dp, vertical = 16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            TextField(
                text = state.weight,
                label = stringResource(Res.string.product_weight) + String.SPACE + "(${state.productMeasurementUnit.longDisplayName})",
                backgroundColor = FitnessAppTheme.colors.backgroundSecondary,
                modifier = Modifier
                    .fillMaxWidth()
                    .weight(1f),
                keyboardOptions = KeyboardOptions.Default.copy(
                    keyboardType = KeyboardType.Number,
                ),
                onValueChange = {
                    onEvent(ProductEvent.WeightChanged(it))
                }
            )

            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .padding(start = 4.dp, top = 6.dp)
                    .clip(shape = RoundedCornerShape(8.dp))
                    .clickable {
                        onEvent(ProductEvent.OnMeasurementUnitClicked)
                    }
                    .padding(start = 4.dp)
                    .padding(vertical = 8.dp),
            ) {
                Text(
                    text = state.productMeasurementUnit.longDisplayName,
                    style = FitnessAppTheme.typography.labelLarge,
                    color = FitnessAppTheme.colors.contentPrimary,
                    modifier = Modifier.padding(end = 2.dp),
                )

                Icon(
                    imageVector = Icons.Default.ArrowDropDown,
                    contentDescription = null,
                )
            }
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .horizontalScroll(state = rememberScrollState())
                .padding(horizontal = 16.dp)
                .padding(bottom = 16.dp),
            horizontalArrangement = Arrangement.spacedBy(12.dp)
        ) {
            listOf(50, 100, 150, 200).forEach { weight ->
                Button(
                    backgroundColor = FitnessAppTheme.colors.primary,
                    modifier = Modifier.weight(1f),
                    onClick = {
                        onEvent(ProductEvent.OnQuickWeightButtonClicked(weight))
                    },
                    content = {
                        MediumButtonTextContent(text = "$weight ${state.productMeasurementUnit.shortDisplayName}")
                    }
                )
            }
        }

        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(bottom = 24.dp),
        ) {
            DonutChart(
                slices = state.nutritionValuesPercentages.map { (nutritionValue, value) ->
                    ChartSlice(
                        value = value,
                        color = nutritionValue.color,
                    )
                },
                strokeWidth = 16.dp,
                lineGap = 4.dp,
                lineColor = FitnessAppTheme.colors.border,
                modifier = Modifier
                    .size(160.dp)
                    .padding(horizontal = 16.dp),
                centerText = {
                    Text(
                        text = "${state.currentNutritionValues.calories} ${stringResource(Res.string.calories_short_name)}",
                        style = FitnessAppTheme.typography.labelLarge,
                        color = FitnessAppTheme.colors.contentPrimary,
                        textAlign = TextAlign.Center,
                        maxLines = 3,
                        overflow = TextOverflow.Ellipsis,
                        modifier = Modifier.padding(20.dp)
                    )
                }
            )

            Column(
                modifier = Modifier
                    .weight(1f),
                verticalArrangement = Arrangement.spacedBy(4.dp)
            ) {
                state.nutritionValuesPercentages.forEach { (nutritionValue, value) ->
                    NutritionValueRow(
                        label = when (nutritionValue) {
                            NutritionValue.CARBOHYDRATES -> stringResource(Res.string.carbs)
                            NutritionValue.PROTEIN -> stringResource(Res.string.protein)
                            NutritionValue.FAT -> stringResource(Res.string.fat)
                        },
                        value = when (nutritionValue) {
                            NutritionValue.CARBOHYDRATES -> state.currentNutritionValues.carbohydrates
                            NutritionValue.PROTEIN -> state.currentNutritionValues.protein
                            NutritionValue.FAT -> state.currentNutritionValues.fat
                        },
                        percentage = value,
                        percentageColor = nutritionValue.color,
                        measurementUnit = state.productMeasurementUnit,
                    )
                }
            }
        }
    }
}

@Composable
private fun NutritionValueRow(
    label: String,
    value: Double,
    percentage: Float?,
    percentageColor: Color = FitnessAppTheme.colors.contentPrimary,
    measurementUnit: MeasurementUnit,
) {
    Column(
        modifier = Modifier.padding(end = 16.dp),
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            modifier = Modifier.padding(vertical = 8.dp, horizontal = 4.dp),
        ) {
            Box(
                modifier = Modifier
                    .height(24.dp)
                    .width(6.dp)
                    .background(
                        color = percentageColor,
                        shape = RoundedCornerShape(2.dp),
                    )
            )

            Text(
                text = "$label${String.SEMICOLON}",
                style = FitnessAppTheme.typography.labelMedium,
                color = FitnessAppTheme.colors.contentPrimary,
                modifier = Modifier.padding(start = 4.dp),
            )

            Text(
                text = "$value ${measurementUnit.shortDisplayName}",
                style = FitnessAppTheme.typography.labelMedium,
                color = FitnessAppTheme.colors.contentPrimary,
                textAlign = TextAlign.End,
                maxLines = 1,
                overflow = TextOverflow.Ellipsis,
                modifier = Modifier
                    .weight(1f)
                    .padding(horizontal = 4.dp)
            )

            percentage?.let {
                Text(
                    text = "($percentage${String.PERCENTAGE})",
                    style = FitnessAppTheme.typography.labelMedium,
                    color = percentageColor,
                )
            }
        }

        HorizontalDivider(
            modifier = Modifier.fillMaxWidth(),
            color = FitnessAppTheme.colors.border,
        )
    }
}

private val NutritionValue.color
    @Composable
    get() = when (this) {
        NutritionValue.CARBOHYDRATES -> FitnessAppTheme.colors.carbohydrates
        NutritionValue.PROTEIN -> FitnessAppTheme.colors.protein
        NutritionValue.FAT -> FitnessAppTheme.colors.fat
    }