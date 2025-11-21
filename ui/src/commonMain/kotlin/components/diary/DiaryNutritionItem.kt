package components.diary

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

@Composable
fun DiaryNutritionItem(
    modifier: Modifier = Modifier,
    name: String,
    value: Double,
    color: Color,
) {
    Row(
        modifier = modifier,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Box(
            modifier = Modifier
                .size(width = 4.dp, height = 28.dp)
                .background(color = color, shape = RoundedCornerShape(4.dp))
        )

        Spacer(modifier = Modifier.width(4.dp))

        Column {
            // TODO: Replace with dynamic measurement unit
            Text(
                text = "$value g",
                style = FitnessAppTheme.typography.labelMedium,
                color = FitnessAppTheme.colors.contentPrimary,
            )

            Text(
                text = name,
                style = FitnessAppTheme.typography.bodySmall,
                color = FitnessAppTheme.colors.contentSecondary,
            )
        }
    }
}