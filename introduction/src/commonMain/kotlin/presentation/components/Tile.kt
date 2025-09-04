package presentation.components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

@Composable
fun Tile(
    modifier: Modifier = Modifier,
    content: String,
    isSelected: Boolean,
    onItemClick: () -> Unit,
) {
    OutlinedButton(
        onClick = {
            onItemClick()
        },
        border = BorderStroke(1.dp, FitnessAppTheme.colors.primary),
        shape = RoundedCornerShape(50),
        colors = ButtonDefaults.outlinedButtonColors(
            contentColor = if (isSelected) FitnessAppTheme.colors.contentPrimary else FitnessAppTheme.colors.contentSecondary,
            containerColor = if (isSelected) FitnessAppTheme.colors.primary else FitnessAppTheme.colors.surface
        ),
        modifier = modifier,
    ) {
        Text(
            text = content,
            style = FitnessAppTheme.typography.labelLarge,
            color = if (isSelected) FitnessAppTheme.colors.contentPrimary else FitnessAppTheme.colors.contentSecondary,
            textAlign = TextAlign.Center,
            modifier = Modifier.padding(horizontal = 5.dp, vertical = 2.dp)
        )
    }
} 