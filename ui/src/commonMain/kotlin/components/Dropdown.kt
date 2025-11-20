package components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.OutlinedTextFieldDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import theme.FitnessAppTheme
import utils.noRippleClickable

@Composable
fun AppDropdown(
    modifier: Modifier = Modifier,
    text: String,
    label: String,
    backgroundColor: Color = FitnessAppTheme.colors.background,
    onClick: () -> Unit,
) {
    OutlinedTextField(
        value = text,
        enabled = false,
        onValueChange = {},
        modifier = modifier
            .fillMaxWidth()
            .noRippleClickable(onClick = onClick),
        textStyle = FitnessAppTheme.typography.bodyLarge,
        label = {
            Text(
                text = label,
                style = if (text.isEmpty()) {
                    FitnessAppTheme.typography.bodyLarge
                } else {
                    FitnessAppTheme.typography.bodySmall
                }
            )
        },
        trailingIcon = {
            Icon(
                imageVector = Icons.Default.KeyboardArrowDown,
                contentDescription = null,
                modifier = Modifier.padding(end = 12.dp)
            )
        },
        colors = OutlinedTextFieldDefaults.colors(disabledBorderColor = FitnessAppTheme.colors.contentSecondary).copy(
            disabledTextColor = FitnessAppTheme.colors.contentPrimary,
            disabledLabelColor = FitnessAppTheme.colors.contentPrimary,
            disabledContainerColor = backgroundColor,
            disabledTrailingIconColor = FitnessAppTheme.colors.contentPrimary,
        ),
    )
}