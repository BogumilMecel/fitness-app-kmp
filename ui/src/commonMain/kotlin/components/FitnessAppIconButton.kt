package components

import androidx.compose.material.IconButton
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import theme.FitnessAppTheme

@Composable
fun FitnessAppIconButton(
    modifier: Modifier = Modifier,
    icon: Icon,
    enabled: Boolean = true,
    tint: Color = FitnessAppTheme.colors.contentPrimary,
    onClick: () -> Unit
) {
    IconButton(
        modifier = modifier,
        enabled = enabled,
        onClick = onClick,
    ) {
        FitnessAppIcon(
            icon = icon,
            tint = tint
        )
    }
}