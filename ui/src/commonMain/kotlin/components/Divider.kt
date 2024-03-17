package components

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

@Composable
fun Divider(
    modifier: Modifier = Modifier,
    text: String? = null
) {
    Box(
        modifier = modifier,
        contentAlignment = Alignment.Center
    ) {
        Box(
            modifier = Modifier
                .height(1.dp)
                .fillMaxWidth()
                .background(color = FitnessAppTheme.colors.contentSecondary)
        )

        text?.let {
            Text(
                text = it,
                style = FitnessAppTheme.typography.bodyMedium,
                color = FitnessAppTheme.colors.contentSecondary,
                modifier = Modifier
                    .background(color = FitnessAppTheme.colors.background)
                    .padding(horizontal = 4.dp)
            )
        }
    }
}