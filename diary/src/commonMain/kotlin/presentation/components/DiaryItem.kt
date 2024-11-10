package presentation.components

import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.combinedClickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

@OptIn(ExperimentalFoundationApi::class)
@Composable
fun DiaryItem(
    searchItemParams: SearchItemParams,
    onItemClick: () -> Unit,
    onItemLongClick: () -> Unit,
) = with(searchItemParams) {
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

data class SearchItemParams(
    val name: String,
    val textBelowName: String,
    val endText: String,
)