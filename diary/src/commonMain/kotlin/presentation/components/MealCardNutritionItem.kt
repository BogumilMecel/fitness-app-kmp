package presentation.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.HorizontalProgressIndicator
import components.HorizontalSpacer
import components.VerticalSpacer
import theme.FitnessAppTheme

@Composable
fun MealCardNutritionItem(
    currentValue: Int,
    wantedValue: Int,
    name: String,
    modifier: Modifier
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        HorizontalProgressIndicator(
            progress = if (wantedValue.toFloat() > 0.0f) (currentValue.toFloat() / wantedValue.toFloat() * 100.0f) else 0.0f
        )

        VerticalSpacer(size = 6.dp)

        Column {
            Text(
                text = currentValue.toString(),
                style = FitnessAppTheme.typography.bodyLarge,
                color = FitnessAppTheme.colors.contentPrimary,
            )

            Text(
                text = name,
                style = FitnessAppTheme.typography.bodyMedium,
                color = FitnessAppTheme.colors.contentSecondary,
            )
        }
    }
}