package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

@Composable
fun FitnessAppButton(
    modifier: Modifier = Modifier,
    onClick: () -> Unit,
    text: String,
    startIcon: Icon? = null,
    endIcon: Icon? = null
) {
    Button(
        modifier = modifier
            .heightIn(min = 48.dp)
            .fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = FitnessAppTheme.colors.primary,
            contentColor = FitnessAppTheme.colors.onPrimary
        ),
        content = {
            Row(horizontalArrangement = Arrangement.spacedBy(4.dp)) {
                startIcon?.let {
                    FitnessAppIcon(icon = startIcon)
                }

                Text(
                    text = text,
                    style = FitnessAppTheme.typography.labelLarge
                )

                endIcon?.let {
                    startIcon?.let {
                        FitnessAppIcon(icon = startIcon)
                    }
                }
            }
        }
    )
}