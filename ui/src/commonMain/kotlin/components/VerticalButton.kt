package components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme

@Composable
fun VerticalButton(
    modifier: Modifier = Modifier,
    style: FitnessAppButtonStyle,
    text: String,
    icon: ImageVector,
    onClick: () -> Unit,
) {
    FitnessAppButtonContent(
        modifier = modifier,
        style = style,
        onClick = onClick,
        content = {
            Column(
                modifier = Modifier.padding(vertical = 4.dp, horizontal = 8.dp),
                verticalArrangement = Arrangement.Center,
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Icon(
                    imageVector = icon,
                    contentDescription = null,
                    tint = style.contentColor,
                )

                VerticalSpacer(size = 4.dp)

                Text(
                    text = text,
                    style = FitnessAppTheme.typography.labelLarge,
                    color = style.contentColor,
                    maxLines = 1
                )
            }
        }
    )
}