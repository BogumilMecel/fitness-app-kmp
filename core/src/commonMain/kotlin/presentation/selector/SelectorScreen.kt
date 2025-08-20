package presentation.selector

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.KeyboardArrowRight
import androidx.compose.material3.HorizontalDivider
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import components.SheetTopBar
import components.VerticalSpacer
import theme.FitnessAppTheme

@Composable
fun SelectorScreen(
    state: SelectorState,
    onEvent: (SelectorEvent) -> Unit
) {
    Column(
        modifier = Modifier.verticalScroll(rememberScrollState())
    ) {
        SheetTopBar(
            title = state.title,
            onBackPressed = {
                onEvent(SelectorEvent.OnBackPressed)
            }
        )

        state.items.forEach { selectorItem ->
            SelectorItem(
                text = selectorItem.label,
                onClick = {
                    onEvent(SelectorEvent.OnItemSelected(selectorItem))
                }
            )
        }

        VerticalSpacer(size = 64.dp)
    }
}

@Composable
private fun SelectorItem(
    text: String,
    onClick: () -> Unit,
) {
    Column(
        modifier = Modifier
            .clickable(onClick = onClick)
            .padding(horizontal = 16.dp)
    ) {
        Row(
            modifier = Modifier.fillMaxWidth(),
            verticalAlignment = Alignment.CenterVertically,
        ) {
            Text(
                text = text,
                style = FitnessAppTheme.typography.bodyLarge,
                color = FitnessAppTheme.colors.contentPrimary,
                modifier = Modifier
                    .padding(vertical = 16.dp)
                    .weight(1f)
            )

            Icon(
                imageVector = Icons.AutoMirrored.Default.KeyboardArrowRight,
                contentDescription = null,
                tint = FitnessAppTheme.colors.contentPrimary,
            )
        }

        HorizontalDivider()
    }
}