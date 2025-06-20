package components

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.indication
import androidx.compose.foundation.interaction.MutableInteractionSource
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.RowScope
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.heightIn
import androidx.compose.material.ripple
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

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
        enabled = enabled,
        content = {
            Row(
                horizontalArrangement = Arrangement.spacedBy(4.dp),
                verticalAlignment = Alignment.CenterVertically
            ) {
                if (startIcon != null) {
                    Icon(
                        imageVector = startIcon,
                        contentDescription = null,
                        tint = iconColor,
                    )
                }

                Text(
                    text = text.uppercase(),
                    style = FitnessAppTheme.typography.labelLarge,
                    color = style.contentColor
                )

                if (endIcon != null) {
                    Icon(
                        imageVector = endIcon,
                        contentDescription = null,
                        tint = iconColor,
                    )
                }
            }
        },
    )
}

@Composable
internal fun FitnessAppButtonContent(
    modifier: Modifier = Modifier,
    style: FitnessAppButtonStyle = FitnessAppButtonStyle.Primary,
    onClick: () -> Unit,
    enabled: Boolean = true,
    content: @Composable RowScope.() -> Unit,
) {
    val backgroundColor = when (style) {
        FitnessAppButtonStyle.Primary -> FitnessAppTheme.colors.primary
        FitnessAppButtonStyle.Content -> FitnessAppTheme.colors.contentPrimary
        FitnessAppButtonStyle.Secondary -> FitnessAppTheme.colors.background
    }
    Button(
        modifier = modifier
            .heightIn(min = 48.dp)
            .fillMaxWidth()
            .indication(
                interactionSource = MutableInteractionSource(),
                indication = ripple(color = style.contentColor)
            ),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = backgroundColor,
            contentColor = style.contentColor
        ),
        border = when (style) {
            FitnessAppButtonStyle.Secondary -> BorderStroke(
                width = 1.dp,
                color = FitnessAppTheme.colors.contentSecondary
            )
            else -> null
        },
        enabled = enabled,
        content = content
    )
}

@Composable
fun Button(
    modifier: Modifier = Modifier,
    backgroundColor: Color,
    contentColor: Color = FitnessAppTheme.colors.contentPrimary,
    borderStroke: BorderStroke? = null,
    enabled: Boolean = true,
    onClick: () -> Unit,
    content: @Composable RowScope.() -> Unit,
) {
    Button(
        modifier = modifier
            .indication(
                interactionSource = MutableInteractionSource(),
                indication = ripple(color = contentColor),
            ),
        onClick = onClick,
        colors = ButtonDefaults.buttonColors().copy(
            containerColor = backgroundColor,
            contentColor = contentColor,
        ),
        border = borderStroke,
        enabled = enabled,
        content = content
    )
}

enum class FitnessAppButtonStyle {
    Primary, Content, Secondary;

    internal val contentColor
        @Composable
        get() = when (this) {
            Primary -> FitnessAppTheme.colors.onPrimary
            Content -> FitnessAppTheme.colors.onContentPrimary
            Secondary -> FitnessAppTheme.colors.contentPrimary
        }
}