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
import domain.services.ResourcesService
import theme.FitnessAppTheme

import utils.getCaloriesFormatted
import utils.formatWithValue

@Composable
fun DiaryItem(
    params: DiaryItemParams,
    onItemClick: () -> Unit,
    onItemLongClick: () -> Unit,
) = with(params) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .background(color = FitnessAppTheme.colors.backgroundTertiary)
            .combinedClickable(
                onClick = onItemClick,
                onLongClick = onItemLongClick
            )
            .padding(
                vertical = 10.dp,
                horizontal = 16.dp
            ),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        Column {
            Text(
                text = name,
                style = FitnessAppTheme.typography.bodyLarge
            )

            Text(
                text = textBelowName,
                style = FitnessAppTheme.typography.bodyMedium,
                color = FitnessAppTheme.colors.contentSecondary
            )
        }

        Text(
            text = endText,
            style = FitnessAppTheme.typography.bodyMedium,
            color = FitnessAppTheme.colors.contentSecondary
        )
    }
}

data class DiaryItemParams(
    val name: String,
    val textBelowName: String,
    val endText: String,
) {
    companion object {
        suspend fun create(
            product: Product,
            resourcesService: ResourcesService
        ) = DiaryItemParams(
            name = product.name,
            textBelowName = product.measurementUnit.formatWithValue(
                resourcesService = resourcesService,
                value = 100,
            ),
            endText = product.nutritionValues.getCaloriesFormatted(resourcesService),
        )
    }
}