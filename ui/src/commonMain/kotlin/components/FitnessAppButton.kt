package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme
import theme.LocalRippleColor

@Composable
fun FitnessAppButton(
    modifier: Modifier = Modifier,
    style: FitnessAppButtonStyle = FitnessAppButtonStyle.Primary,
    iconColor: Color = style.contentColor,
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true,
    startIcon: ImageVector? = null,
    endIcon: ImageVector? = null
) {
    FitnessAppButtonContent(
        modifier = modifier,
        style = style,
        onClick = onClick,
        text = text,
        enabled = enabled,
        startIcon = startIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = iconColor,
                )
            }
        },
        endIcon = endIcon?.let {
            {
                Icon(
                    imageVector = it,
                    contentDescription = null,
                    tint = iconColor,
                )
            }
        }
    )
}

@Composable
private fun FitnessAppButtonContent(
    modifier: Modifier = Modifier,
    style: FitnessAppButtonStyle = FitnessAppButtonStyle.Primary,
    onClick: () -> Unit,
    text: String,
    enabled: Boolean = true,
    startIcon: (@Composable () -> Unit)? = null,
    endIcon: (@Composable () -> Unit)? = null,
) {
    val backgroundColor = when (style) {
        FitnessAppButtonStyle.Primary -> FitnessAppTheme.colors.primary
        FitnessAppButtonStyle.Content -> FitnessAppTheme.colors.contentPrimary
    }
    LocalRippleColor.provides(
        when (style) {
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
            contentColor = style.contentColor
        ),
        enabled = enabled,
        content = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                startIcon?.invoke()

                Text(
                    text = text.uppercase(),
                    style = FitnessAppTheme.typography.labelLarge,
                    color = style.contentColor
                )

                endIcon?.invoke()
            }
        }
    )
}

enum class FitnessAppButtonStyle {
    Primary, Content;

    internal val contentColor
        @Composable
        get() = when (this) {
            Primary -> FitnessAppTheme.colors.onPrimary
            Content -> FitnessAppTheme.colors.onContentPrimary
        }
}