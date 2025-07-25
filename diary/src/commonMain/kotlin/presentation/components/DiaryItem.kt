package presentation.components

import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import models.Product
import theme.FitnessAppTheme
import utils.formatWithValue
import utils.getCaloriesFormatted

@Composable
fun DiaryItem(
    product: Product,
    onItemClick: (Product) -> Unit,
    onItemLongClick: (Product) -> Unit,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = FitnessAppTheme.colors.backgroundTertiary)
            .combinedClickable(
                onClick = {
                    onItemClick(product)
                },
                onLongClick = {
                    onItemLongClick(product)
                }
            )
            .padding(vertical = 10.dp, horizontal = 16.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = product.name,
                style = FitnessAppTheme.typography.bodyLarge
            )

            Text(
                text = product.measurementUnit.formatWithValue(value = 100),
                style = FitnessAppTheme.typography.bodyMedium,
                color = FitnessAppTheme.colors.contentSecondary
            )
        }

        Text(
            text = product.nutritionValues.getCaloriesFormatted(),
            style = FitnessAppTheme.typography.bodyMedium,
            color = FitnessAppTheme.colors.contentSecondary
        )
    }
}