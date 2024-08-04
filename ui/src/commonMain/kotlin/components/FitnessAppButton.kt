package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme
import theme.LocalRippleColor

@Composable
fun FitnessAppButton(
    modifier: Modifier = Modifier,
    style: FitnessAppButtonStyle = FitnessAppButtonStyle.Primary,
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true,
    startIcon: Icon? = null,
    endIcon: Icon? = null
) {
    val backgroundColor = when(style) {
        FitnessAppButtonStyle.Primary -> FitnessAppTheme.colors.primary
        FitnessAppButtonStyle.Content -> FitnessAppTheme.colors.contentPrimary
    }
    val contentColor = when(style) {
        FitnessAppButtonStyle.Primary -> FitnessAppTheme.colors.onPrimary
        FitnessAppButtonStyle.Content -> FitnessAppTheme.colors.onContentPrimary
    }
    LocalRippleColor.provides(
        when(style) {
            FitnessAppButtonStyle.Primary -> FitnessAppTheme.colors.onPrimary
            FitnessAppButtonStyle.Content -> FitnessAppTheme.colors.onContentPrimary
        }
    )
    Button(
        modifier = modifier
            .heightIn(min = 48.dp)
            .fillMaxWidth(),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors(
            backgroundColor = backgroundColor,
            contentColor = contentColor
        ),
        enabled = enabled,
        content = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                startIcon?.let {
                    FitnessAppIcon(
                        icon = startIcon,
                        tint = contentColor
                    )
                }

                Text(
                    text = text.uppercase(),
                    style = FitnessAppTheme.typography.labelLarge,
                    color = contentColor
                )

                endIcon?.let {
                    startIcon?.let {
                        FitnessAppIcon(
                            icon = startIcon,
                            tint = contentColor
                        )
                    }
                }
            }
        }
    )
}

enum class FitnessAppButtonStyle {
    Primary, Content
}