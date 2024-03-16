package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

@Composable
fun FitnessAppTopBar(
    title: String?,
    subTitle: String? = null,
    onBackPressed: (() -> Unit)? = null,
) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
        horizontalArrangement = Arrangement.SpaceBetween,
        verticalAlignment = Alignment.CenterVertically
    ) {
        onBackPressed?.let {
            FitnessAppIconButton(
                icon = IconVector.Back,
                onClick = onBackPressed,
            )
        }

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            modifier = Modifier
                .weight(1f)
                .padding(vertical = 12.dp)
                .padding(end = 48.dp)
                .then(
                    other = if (onBackPressed == null) {
                        Modifier.padding(start = 48.dp)
                    } else Modifier
                )
        ) {
            title?.let {
                androidx.compose.material.Text(
                    text = title,
                    style = FitnessAppTheme.typography.headlineMedium,
                    color = FitnessAppTheme.colors.contentPrimary
                )
            }
            subTitle?.let {
                androidx.compose.material.Text(
                    text = subTitle,
                    style = FitnessAppTheme.typography.labelSmall,
                    color = FitnessAppTheme.colors.contentSecondary
                )
            }
        }
    }
}